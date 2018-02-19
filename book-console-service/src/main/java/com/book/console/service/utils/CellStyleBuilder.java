package com.book.console.service.utils;

import org.apache.poi.ss.usermodel.*;

/**
 * Created by zino on 08/12/2016.
 */
public class CellStyleBuilder {
    private Workbook wb;

    private BorderStyle borderStyle = BorderStyle.THIN;
    private IndexedColors borderColor = IndexedColors.BLACK;
    private int fontSize = 9;
    private IndexedColors fontColor = IndexedColors.BLACK;
    private boolean fontBold = false;
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private VerticalAlignment verticalAlignment = VerticalAlignment.CENTER;

    public CellStyleBuilder(Workbook wb) {
        this.wb = wb;
    }

    public static CellStyleBuilder create(Workbook wb) {
        return new CellStyleBuilder(wb);
    }

    public CellStyleBuilder setBorderStyle(BorderStyle style) {
        this.borderStyle = style;
        return this;
    }

    public CellStyleBuilder setBorderColor(IndexedColors color) {
        this.borderColor = color;
        return this;
    }

    public CellStyleBuilder setFontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public CellStyleBuilder setFontColor(IndexedColors color) {
        this.fontColor = color;
        return this;
    }

    public CellStyleBuilder setFontBold(boolean bold) {
        this.fontBold = bold;
        return this;
    }

    public CellStyleBuilder setHorizontalAlignment(HorizontalAlignment alignment) {
        this.horizontalAlignment = alignment;
        return this;
    }

    public CellStyleBuilder setVerticalAlignment(VerticalAlignment alignment) {
        this.verticalAlignment = alignment;
        return this;
    }

    public CellStyle build() {
        CellStyle  style = wb.createCellStyle();

        //边框
        style.setBorderBottom(this.borderStyle);
        style.setBorderLeft(this.borderStyle);
        style.setBorderRight(this.borderStyle);
        style.setBorderTop(this.borderStyle);

        style.setTopBorderColor(this.borderColor.getIndex());
        style.setBottomBorderColor(this.borderColor.getIndex());
        style.setLeftBorderColor(this.borderColor.getIndex());
        style.setRightBorderColor(this.borderColor.getIndex());

        style.setAlignment(this.horizontalAlignment);
        style.setVerticalAlignment(this.verticalAlignment);

        //字体
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) this.fontSize);
        font.setFontName("Microsoft Yahei");
        font.setColor(this.fontColor.getIndex());
        font.setBold(this.fontBold);
        style.setFont(font);

        return style;
    }
}
