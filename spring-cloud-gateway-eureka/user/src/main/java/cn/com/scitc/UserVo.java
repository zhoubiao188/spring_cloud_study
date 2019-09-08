/**
 * @program spring-cloud-gateway-eureka
 * @description: 简单vo
 * @author: zhoubiao
 * @create: 2019/09/08 11:05
 */

package cn.com.scitc;

public class UserVo {

     private String nickname;
     private String age;
     private String address;
     private String job;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
