package siso.edu.cn.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "view_bind_user_department", schema = "electric_iot", catalog = "")
public class ViewBindUserDepartmentEntity {
    @JsonProperty("id")
    private long id;
    @JsonProperty("department_id")
    private long departmentId;
    @JsonProperty("department_name")
    private String departmentName;
    @JsonProperty("level")
    private int level;
    @JsonProperty("department_create_time")
    private String departmentCreateTime;
    @JsonProperty("department_is_delete")
    private int departmentIsDelete;
    @JsonProperty("parent_id")
    private long parentId;
    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("login_name")
    private String loginName;
    @JsonProperty("login_password")
    private String loginPassword;
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_create_time")
    private String userCreateTime;
    @JsonProperty("mobile")
    private String mobile;
    @JsonProperty("email")
    private String email;
    @JsonProperty("user_is_delete")
    private int userIsDelete;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "department_id", nullable = false)
    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "department_name", nullable = false, length = 255)
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Basic
    @Column(name = "level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "department_create_time", nullable = false)
    public String getDepartmentCreateTime() {
        return departmentCreateTime;
    }

    public void setDepartmentCreateTime(String departmentCreateTime) {
        this.departmentCreateTime = departmentCreateTime;
    }

    @Basic
    @Column(name = "department_is_delete", nullable = false)
    public int getDepartmentIsDelete() {
        return departmentIsDelete;
    }

    public void setDepartmentIsDelete(int departmentIsDelete) {
        this.departmentIsDelete = departmentIsDelete;
    }

    @Basic
    @Column(name = "parent_id", nullable = false)
    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "login_name", nullable = false, length = 45)
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Basic
    @Column(name = "login_password", nullable = false, length = 45)
    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    @Basic
    @Column(name = "user_name", nullable = false, length = 45)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "user_create_time", nullable = false)
    public String getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(String userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 45)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "user_is_delete", nullable = false)
    public int getUserIsDelete() {
        return userIsDelete;
    }

    public void setUserIsDelete(int userIsDelete) {
        this.userIsDelete = userIsDelete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ViewBindUserDepartmentEntity that = (ViewBindUserDepartmentEntity) o;

        if (id != that.id) return false;
        if (departmentId != that.departmentId) return false;
        if (level != that.level) return false;
        if (departmentIsDelete != that.departmentIsDelete) return false;
        if (parentId != that.parentId) return false;
        if (userId != that.userId) return false;
        if (userIsDelete != that.userIsDelete) return false;
        if (departmentName != null ? !departmentName.equals(that.departmentName) : that.departmentName != null)
            return false;
        if (departmentCreateTime != null ? !departmentCreateTime.equals(that.departmentCreateTime) : that.departmentCreateTime != null)
            return false;
        if (loginName != null ? !loginName.equals(that.loginName) : that.loginName != null) return false;
        if (loginPassword != null ? !loginPassword.equals(that.loginPassword) : that.loginPassword != null)
            return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (userCreateTime != null ? !userCreateTime.equals(that.userCreateTime) : that.userCreateTime != null)
            return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (departmentId ^ (departmentId >>> 32));
        result = 31 * result + (departmentName != null ? departmentName.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + (departmentCreateTime != null ? departmentCreateTime.hashCode() : 0);
        result = 31 * result + departmentIsDelete;
        result = 31 * result + (int) (parentId ^ (parentId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (loginName != null ? loginName.hashCode() : 0);
        result = 31 * result + (loginPassword != null ? loginPassword.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (userCreateTime != null ? userCreateTime.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + userIsDelete;
        return result;
    }
}
