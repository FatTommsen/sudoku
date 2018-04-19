package de.fhaachen.navi.sudoku;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;

public class TextField extends android.support.v7.widget.AppCompatButton {
    private static int COLOR_USER = Color.BLUE;
    private static int COLOR_GENERATOR = Color.BLACK;
    private static int COLOR_HINT = Color.RED;

    private Cell cell;

    public TextField(Context context, Cell c) {
        super(context);
        this.cell = c;
        setBackground(this.getResources().getDrawable(R.drawable.cell_border));
        if (c.isVisible()) {
            setTextColor(COLOR_GENERATOR);
            setText(cell.getValue() + "");
        } else {
            setTextColor(COLOR_USER);
            setText(" ");
        }
    }

    public void setSize(int pixels) {
        super.setHeight(pixels);
        super.setMinimumHeight(pixels);
        super.setWidth(pixels);
        super.setMinimumWidth(pixels);
        super.setTextSize(TypedValue.COMPLEX_UNIT_PX, pixels / 3);
    }

    public boolean isFromBeginning() {
        return cell.isFromBeginning();
    }

    public Cell getCell() {
        return cell;
    }

    public void setFromBeginning(boolean bValue){
        cell.setFromBeginning(bValue);
    }

    public static int getColorHint() {
        return COLOR_HINT;
    }
}