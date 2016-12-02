package productions.funky.com.smokenomore.util;

import productions.funky.com.smokenomore.R;

/**
 * Created by RezaulAkram on 10/29/2016.
 */
public class ResourceMapper {
    public static int getColorForCount(int count)
    {
        int colorId = R.color.CounterBlue;
        switch (count) {
            case 0:
                colorId = R.color.CounterBlue;
                break;
            case 1:
            case 2:
            case 3:
                colorId = R.color.CounterYellow1;
                break;
            case 4:
            case 5:
            case 6:
                colorId = R.color.CounterYellow2;
                break;
            case 7:
            case 8:
            case 9:
                colorId = R.color.CounterYellow3;
                break;
            case 10:
            case 11:
            case 12:
                colorId = R.color.CounterYellow4;
                break;
            case 13:
            case 14:
            case 15:
                colorId = R.color.CounterYellow5;
                break;
            case 16:
            case 17:
            case 18:
                colorId = R.color.CounterYellow6;
                break;
            case 19:
            case 20:
            case 21:
                colorId = R.color.CounterYellow7;
                break;
            default:
                colorId = R.color.CounterYellow8;
                break;
        }
        return colorId;
    }

    public static int getDrawableForCount(int count)
    {
        int drawableId = R.drawable.appbackground;
        switch (count) {
            case 0:
                drawableId = R.drawable.appbackground;
                break;
            case 1:
            case 2:
            case 3:
                drawableId = R.drawable.appbackground_yellow;
                break;
            case 4:
            case 5:
            case 6:
                drawableId = R.drawable.appbackground_yellow_2;
                break;
            case 7:
            case 8:
            case 9:
                drawableId = R.drawable.appbackground_yellow_3;
                break;
            case 10:
            case 11:
            case 12:
                drawableId = R.drawable.appbackground_yellow_4;
                break;
            case 13:
            case 14:
            case 15:
                drawableId = R.drawable.appbackground_yellow_5;
                break;
            case 16:
            case 17:
            case 18:
                drawableId = R.drawable.appbackground_yellow_6;
                break;
            case 19:
            case 20:
            case 21:
                drawableId = R.drawable.appbackground_yellow_7;
                break;
            default:
                drawableId = R.drawable.appbackground_yellow_8;
                break;
        }
        return drawableId;
    }
}
