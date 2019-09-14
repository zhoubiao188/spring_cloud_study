/**
 * @program gateway-gaoji
 * @description: vo类
 * @author: zhoubiao
 * @create: 2019/09/14 11:18
 */

package cn.com.scitc;

public class UserVo {
    private String nickname;
    private String job;
    private String address;
    private Integer age;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
