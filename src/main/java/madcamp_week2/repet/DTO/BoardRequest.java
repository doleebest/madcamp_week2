package madcamp_week2.repet.DTO;

public class BoardRequest {
    private String title;
    private String content;

    // 기본 생성자
    public BoardRequest() {
    }

    // 생성자
    public BoardRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getter 및 Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}