/**
 * @program RestTemplate
 * @description: 服务消费者
 * @author: zhoubiao
 * @create: 2019/07/29 08:33
 */

package cn.com.scitc.consumer;

import cn.com.scitc.domain.UserDTO;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class SayUseHelloController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/hello")
    public String hello(String name) {
        //拿到服务提供商
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        //拿到第一个实例
        ServiceInstance instance = list.get(0);

        //得到主机号
        String host = instance.getHost();
        //得到端口号
        int port = instance.getPort();

        //拼接完整的请求url
        String url = "http://" + host + ":" + port + "/hello?name={1}";
        //restTemple 实际返回的是一个ResponseEntity 的实例
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, name);

        StrBuilder sb = new StrBuilder();
        //拿到状态码
        HttpStatus statusCode = responseEntity.getStatusCode();
        //拿到响应体
        String body= responseEntity.getBody();
        sb.append("statusCode:")
                .append(statusCode)
                .append("<br/>")
                .append("body:")
                .append(body)
                .append("<br/>");
        //拿到响应头header
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            sb.append(s)
                    .append(":")
                    .append(headers.get(s))
                    .append("</br>");
        }
        return sb.toString();
    }

    @GetMapping("/hello2")
    public String hello2(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello?name={name}";
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);
        StringBuffer sb = new StringBuffer();
        HttpStatus statusCode = responseEntity.getStatusCode();
        String body = responseEntity.getBody();
        sb.append("statusCode:")
                .append(statusCode)
                .append("</br>")
                .append("body:")
                .append(body)
                .append("</br>");
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keyset = headers.keySet();
        for (String s : keyset) {
            sb.append(s)
                    .append(":")
                    .append(headers.get(s))
                    .append("</br>");
        }

        return sb.toString();

    }

    @GetMapping("/hello3")
    public String hello3(String name) throws UnsupportedEncodingException {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello?name=" + URLEncoder.encode(name, "UTF-8");
        URI uri = URI.create(url);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri,String.class);
        StringBuffer sb = new StringBuffer();
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        HttpHeaders headers = responseEntity.getHeaders();
        sb.append("statusCode:")
                .append(statusCode)
                .append("</br>")
                .append("body:")
                .append(body)
                .append("</br>");
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            sb.append(s)
                    .append(":")
                    .append(headers.get(s))
                    .append("</br>");
        }

        return sb.toString();

    }

    @GetMapping("/hello4")
    public String hello4(String name) throws UnsupportedEncodingException {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance  instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello?name=" + URLEncoder.encode(name, "UTF-8");
        URI uri = URI.create(url);
        String s = restTemplate.getForObject(uri, String.class);
        return s;
    }

    /**
     * 可以使用 MultiValueMap来传递name
     * @param MultiValueMap
     * @return
     */
    @GetMapping("/hello5")
    public String hello5(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello2";
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("name", name);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,  map, String.class);
        return responseEntity.getBody();
    }

    /**
     * postForEntity的第二个参数可以为空
     * @param name
     * @return
     */
    @GetMapping("/hello6")
    public String hello6(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/hello2?name={1}";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, null,String.class, name);
        return responseEntity.getBody();
    }

    @PostMapping("/hello7")
    public ResponseEntity<UserDTO> hello7(@RequestBody UserDTO userDTO) {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/user";
        UserDTO u = new UserDTO();
        u.setNickname(userDTO.getNickname());
        u.setAddress(userDTO.getAddress());
        ResponseEntity<UserDTO> responseEntity = restTemplate.postForEntity(url, u, UserDTO.class);
        return new ResponseEntity<UserDTO>(responseEntity.getBody(),HttpStatus.OK);
    }

    @GetMapping("/hello8")
    public String hello8() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/register";
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("nickname", "技术无止境");
        map.add("address", "四川成都");
        URI uri = restTemplate.postForLocation(url, map);
        String s = restTemplate.getForObject(uri, String.class);
        return s;
    }

    @GetMapping("/hello9")
    public void hello9() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host  = instance.getHost();
        int port = instance.getPort();
        String url1 = "http://" + host + ":" + port + "/user/name";
        String url2 = "http://" + host + ":" + port + "/user/address";
        MultiValueMap map =  new LinkedMultiValueMap();
        map.add("nickname","技术无止境");
        map.add("address", "四川成都");
        restTemplate.put(url1, map);
        UserDTO u = new UserDTO();
        u.setNickname("独孤剑圣");
        u.setAddress("上海");
        restTemplate.put(url2, u);
    }

    @GetMapping("/hello10")
    public void hello10() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url1 = "http://" + host + ":" + port + "/user/{1}";
        String url2 = "http://" + host + ":" + port + "/user/?nickname={nickname}";
        Map<String,String> map = new HashMap<>();
        map.put("nickname","技术无止境");
        restTemplate.delete(url1, 99);
        restTemplate.delete(url2, map);
    }

    @GetMapping("/hello11")
    public void hello11() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/diyheader";
        restTemplate.setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException, IOException {
                HttpHeaders headers = request.getHeaders();
                headers.add("cookie","i love java");
                return execution.execute(request,body);
            }
        }));
        String s = restTemplate.getForObject(url, String.class);
        System.out.println(s);
    }

    @GetMapping("/hello12")
    public void hello12() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        ServiceInstance instance = list.get(0);
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://" + host + ":" + port + "/diyheader";
        HttpHeaders headers = new HttpHeaders();
        headers.add("cookie","i love gir");
        HttpEntity<MultiValueMap<String,String>> request =  new HttpEntity<>(null,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, request, String.class);
        System.out.println(responseEntity.getBody());
    }


}
