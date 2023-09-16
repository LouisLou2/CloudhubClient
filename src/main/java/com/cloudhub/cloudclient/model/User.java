package com.cloudhub.cloudclient.model;

import cn.hutool.core.util.StrUtil;
import com.cloudhub.cloudclient.common.constant.AppConstants;
import com.cloudhub.cloudclient.utils.DefaultGenerator;

/**
 * 用户模型
 */
public class User extends BaseParameter {

    /**
     * 主键
     */
    private long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private long phone;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 账户总容量
     */
    private long totalSpace;

    /**
     * 账户已用容量
     */
    private long usedSpace;
    /**
     * 性别
     */
    private int gender;
    /**
     * 年龄
     */
    private int age;
    /**
     * 生日
     */
    private long birthday;

    public static User initial() {
        User user = new User();
        user.setName(StrUtil.EMPTY);
        user.setEmail(StrUtil.EMPTY);
        user.setPassword(StrUtil.EMPTY);
        user.setAvatar(DefaultGenerator.RandomGravatar("user"));
        user.setBirthday(0L);
        user.setAge(0);
        user.setGender(-1);
        user.setPhone(0);
        user.setTotalSpace(AppConstants.Account.TOTAL_SPACE_SIZE);
        user.setUsedSpace(0L);
        return user;
    }
    public User(){
        super();
    }
    public static User initial(String userName, long phone,String password) {
        User user = initial();
        user.setName(userName);
        user.setPassword(password);
        user.setPhone(phone);
        return user;
    }
    public static User pack(long userId, long usedSpace) {
        User user = initial();
        user.setId(userId);
        user.setUsedSpace(usedSpace);
        return user;
    }

    @Override
    public String toString() {
        return "User["
                + " id=" + id + ","
                + " name=" + name + ","
                + " email=" + email + ","
                + " password=" + password + ","
                + " avatar=" + avatar + ","
                + " birthday=" + birthday + ","
                + " age=" + age + ","
                + " gender=" + gender + ","
                + " phone=" + phone + ","
                + " totalSpace=" + totalSpace + ","
                + " usedSpaceSize=" + usedSpace
                + " ]"
                + " "
                + super.toString();
    }

    public User(long id, String name, String password, long phone, String avatar, String email, long totalSpace, long usedSpace, int gender, int age, long birthday) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.avatar = avatar;
        this.email = email;
        this.totalSpace = totalSpace;
        this.usedSpace = usedSpace;
        this.gender = gender;
        this.age = age;
        this.birthday = birthday;
    }
    //all setters and getters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTotalSpace() {
        return totalSpace;
    }

    public void setTotalSpace(long totalSpace) {
        this.totalSpace = totalSpace;
    }

    public long getUsedSpace() {
        return usedSpace;
    }

    public void setUsedSpace(long usedSpace) {
        this.usedSpace = usedSpace;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }
}
