package net.driftingsouls.ds2.interfaces.framework;

import net.driftingsouls.ds2.interfaces.framework.templates.ITemplateEngine;

import java.util.Set;

public interface IBasicUser {
    int getId();

    Set<IPermission> getPermissions();

    void attachToUser(IBasicUser user);

    void setTemplateVars(ITemplateEngine templateEngine);

    void setTemplateVars(ITemplateEngine templateEngine, String prefix);

    int getAccessLevel();

    boolean isAdmin();

    String getUN();

    String getName();

    void setName(String name);

    String getPassword();

    void setPassword(String pw);

    int getInactivity();

    void setInactivity(int inakt);

    int getSignup();

    String getEmail();

    int getLoginFailedCount();

    void setLoginFailedCount(int count);

    String getNickname();

    void setNickname(String nick);

    String getPlainname();

    boolean getDisabled();

    void setDisabled(boolean value);

    int getVersion();

    void setEmail(String email);

    void setAccesslevel(int accesslevel);
}
