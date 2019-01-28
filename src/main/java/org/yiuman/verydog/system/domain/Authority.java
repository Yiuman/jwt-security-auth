package org.yiuman.verydog.system.domain;

import javax.persistence.*;

/**
 * @author Yiuman
 * @date 2018/8/28
 */
@Entity
@Table(name = "sys_authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,name = "authority_name")
    private String authorityName;

    @Column(unique = true,nullable = false,name = "authority_url")
    private String url;

    public Authority() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        return id != null ? id.equals(authority.id) : authority.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", authorityName='" + authorityName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
