package ru.samsung.testapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import java.util.List;

public class MyEditText extends androidx.appcompat.widget.AppCompatEditText {

    private int width;
    private List<String> pages;
    private int page;

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (pages != null) {
                    if (event.getX() > width * 0.8 && page < pages.size() - 1) {
                        page++;
                        setPage(page);
                    } else if (event.getX() < width * 0.2 && page > 0){
                        page--;
                        setPage(page);
                    }
                }
        }
        return true;
    }

    public void setText(List<String> pages, int page){
        this.pages = pages;
        this.page = page;
        setPage(page);
    }

    public List<String> getPages() {
        return pages;
    }

    public void setPages(List<String> pages) {
        this.pages = pages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        if (pages != null) {
            this.page = page;
            setText(pages.get(page));
        }
    }
}
