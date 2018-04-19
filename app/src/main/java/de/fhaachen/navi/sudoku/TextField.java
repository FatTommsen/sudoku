package de.fhaachen.navi.sudoku;

import android.content.Context;
import android.graphics.Color;

public class TextField extends android.support.v7.widget.AppCompatButton {
    private static int COLOR_USER = Color.BLUE;
    private static int COLOR_GENERATOR = Color.BLACK;

    private Cell cell;

    public TextField(Context context, Cell c) {
        super(context);
        this.cell = c;
        setBackground(this.getResources().getDrawable(R.drawable.cell_border));
        if (c.isVisible()) {
            setTextColor(COLOR_GENERATOR);
            setText(c.getValue() + "");
        } else {
            setTextColor(COLOR_USER);
            setText(" ");
        }
    }

    public boolean isFromBeginning() {
        return cell.isFromBeginning();
    }
}