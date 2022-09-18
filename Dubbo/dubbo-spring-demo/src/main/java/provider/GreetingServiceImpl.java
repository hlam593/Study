package provider;

import service.GreetingService;

/**
 * @author hlam
 * @date 2022/9/18
 */
public class GreetingServiceImpl implements GreetingService {
    @Override
    public String sayHi(String name) {
        return "Hi，" + name;
    }
}
