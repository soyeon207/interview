package s2017s06.kr.hs.mirim.interview;

import java.util.Date;

public class list_item {
    private String nickname;
    private String title;
    private String write_date;

    public list_item(String nickname, String title, String write_date) {
        this.nickname = nickname;
        this.title = title;
        this.write_date = write_date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWrite_date() {
        return write_date;
    }

    public void setWrite_date(String write_date) {
        this.write_date = write_date;
    }
}
