package com.person.service.iml;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.person.api.entity.UserEntity;
import com.person.api.IConsumerService;
import com.person.service.feign.ProviderServiceFeign;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerServiceIml implements IConsumerService {


    @Autowired
    private  ProviderServiceFeign providerServiceFeign;

    @RequestMapping("/")
    @Override
    public String index() {
        return "消费服务";
    }

    /**
     * 通过providerServiceFeign(feign客户端)直接调用参数异常：
     *         解决方案：在该feign调用的服务的参数添加@requestparam;
     * @param name
     * @return
     */

    @RequestMapping("/consume")
    @Override
    public String consumer(String name) {
        UserEntity instance = providerServiceFeign.getInstance(name);
        return null == instance ? "没有该用户":instance.toString();
    }
    @RequestMapping("/info")
    @Override
    public String getUserInfo() {
        UserEntity userIfo = providerServiceFeign.getUserIfo();
        return null == userIfo ? "":userIfo.toString();
    }


    /**
     * 没有解决服务雪崩效应
     * 测试：
     *    01： 验证tomcat是否只有一个线程池(查看线程名称)
     *    02：测压导致服务雪崩效应显示；
     *    测试：通过feign调用其他服务，若网络有延迟，是否会出现异常
     *                     异常：Read timed out
     *                      解决方案：
     *                         设置ribbon的建立连接时间和建立连接之后获取资源时间(默认：ribbon是开启的)；
     *  @return
     */
    @RequestMapping("/msginfo")
    @Override
    public String getMessage() {
        String msg = providerServiceFeign.msgprovider();
        return StringUtils.isBlank(msg)? "没有获取到信息":msg;
    }
    /**
     * 解决服务雪崩效应
     * 测试：
     *    01： 验证tomcat是否只有一个线程池(查看线程名称)
     *    02：测压导致服务雪崩效应显示；
     *    03：@HystrixCommand：
     *           默认开启线程池隔离机制、服务熔断(默认：阈值为10)。服务降级
     *    04：hystrix： 默认响应超时时间1秒(若获取响应，则执行服务降级)
     *  @return
     */
    @RequestMapping("/getMessageHystrix")
    @HystrixCommand(fallbackMethod = "getUserInfoHystrixFallback")
    public String getMessageHystrix() {
        System.out.println("获取用户信息的，当前线程:"+Thread.currentThread().getName());
        String msg = providerServiceFeign.msgprovider();
        return StringUtils.isBlank(msg)? "没有获取到信息":msg;
    }

    /**
     * 解决服务雪崩效应：
     *    01:使用hystrix另外一种方式(建议使用这种方式，因为使用注解方式，过于冗余，且线程隔离，占用CPU高，且这个接口都是使用这一个线程池)：
     *    02:使用接口形式：
     *         01：没有过于冗余，自定义方法；
     *         02：直接本服务的业务代码与调用其他服务的接口不是一个线程池(即调用其他服务异步调用，线程池不一样)；
     *
     *
     *
     *
     *  @return
     */
    @RequestMapping("/getMessageoHystrix02")
    public String getMessageoHystrix02() {
        System.out.println("获取用户信息的，当前线程:"+Thread.currentThread().getName());
        String msg = providerServiceFeign.msgprovider();
        return StringUtils.isBlank(msg)? "没有获取到信息":msg;
    }

    /**
     * 用户测试hystrix的功能：
     *      服务隔离、服务熔断、服务降级
     * @return
     */
    @RequestMapping("/getUserInfoTest")
    public String getMessageTest(){
        System.out.println("获取用户信息的，当前线程:"+Thread.currentThread().getName());
        return "主要测试hystrix的功能";
    }

    //-------服务降级的方法(hystrix注解的方式)--------
    public String  getUserInfoHystrixFallback(){
        return  "当前服务忙，请稍后重试";
    }
}
