package kz.worldskills.a107.ui.home;

public class Item {
    public int suretId;
    public String title;
    public String desc;
    public int baga;
    public int count;

    public Item(int suretId, String title, String desc, int baga, int count){
        this.suretId = suretId;
        this.title = title;
        this.desc = desc;
        this.baga = baga;
        this.count = count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
