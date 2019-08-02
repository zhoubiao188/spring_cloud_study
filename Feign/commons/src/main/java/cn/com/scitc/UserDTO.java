/**
 * @program Feign
 * @description:
 * @author: zhoubiao
 * @create: 2019/08/01 14:23
 */

package cn.com.scitc;

public class UserDTO {
    private Integer id;
    private String nickname;
    private String address;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
