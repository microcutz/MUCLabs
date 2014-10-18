package com.bryanpotts.mondayschild;

/**
 * Created by bryan on 09/10/2014.
 */
public class astrology {

    // *********
    // Variables
    // *********
    // region
    private int iStarSign;
    private String sStarSign;

    // endregion

    // ********************
    // Getteres and Setters
    // ********************

    // region
    public void setiStarSign(int iStarSign)
    {
        this.iStarSign = iStarSign;
    }

    public int getiStarSign()
    {
        return iStarSign;
    }

    public void setsStarSign (String sStarSign)
    {
        this.sStarSign = sStarSign;
    }

    public String getsStarSign()
    {
        return sStarSign;
    }

    // endregion
    // *************************************************
    // Declare Constructor and date manipulation methods
    // *************************************************
    // region
    public astrology()
    {
        // Set default value based on 1st January
        determineStarSign(1, 1);
    }

    public astrology (int aDay, int aMonth)
    {
        // Se default value based on 1st January
        determineStarSign(aDay, aMonth);
    }

    public void determineStarSign (int ssDay, int ssMonth)
    {
        switch (ssMonth) {
            case 1:
                if (ssDay > 20) {
                    sStarSign = "Aquarius";
                } else {
                    sStarSign = sStarSign + "Capricorn</p>";
                }
                break;
            case 2:
                if (ssDay > 19) {
                    sStarSign = "Pisces";
                } else {
                    sStarSign = "Aquarius";
                }
                break;
            case 3:
                if (ssDay > 20) {
                    sStarSign = "Aries";
                } else {
                    sStarSign = "Pisces";
                }
                break;
            case 4:
                if (ssDay > 21) {
                    sStarSign = "Taurus";
                } else {
                    sStarSign = "Aries";
                }
                break;
            case 5:
                if (ssDay > 21) {
                    sStarSign = "Gemini";
                } else {
                    sStarSign = "Taurus";
                }
                break;
            case 6:
                if (ssDay > 21) {
                    sStarSign = "Cancer";
                } else {
                    sStarSign = "Gemini";
                }
                break;
            case 7:
                if (ssDay > 22) {
                    sStarSign = "Leo";
                } else {
                    sStarSign = "Cancer";
                }
                break;
            case 8:
                if (ssDay > 23) {
                    sStarSign = "Virgo";
                } else {
                    sStarSign = "Leo";
                }
                break;
            case 9:
                if (ssDay > 23) {
                    sStarSign = "Libra";
                } else {
                    sStarSign = "Leo";
                }
                break;
            case 10:
                if (ssDay > 23) {
                    sStarSign = "Scorpio";
                } else {
                    sStarSign = "Libra";
                }
            break;
            case 11:
                if (ssDay > 22) {
                    sStarSign = "Sagittarius";
                } else {
                    sStarSign = "Scorpio";
                }
                break;
            case 12:
                if (ssDay > 21) {
                    sStarSign = "Capriconr";
                } else {
                    sStarSign = "Sagittarius";
                }
                break;
            default:
            sStarSign = "I'm sorry I don't recognise this date";
        }
    }
}
