public class CodingBat {

    public boolean sleepIn(boolean weekday, boolean vacation)
    {
        if (weekday == false && vacation == false)
        {
            return true;
        }
        else if (weekday == true && vacation == false)
        {
            return false;
        }
        else if (weekday == false && vacation == true)
        {
            return true;
        }
        else {

            return true;
        }


    }

    public boolean or35(int n)
    {
        if (n%5==0)
        {
            return true;
        }
        else if (n%3 == 0) {
            return true;

        }
        else
        {
            return false;
        }
    }
    public boolean lucky13(int[] n)
    {
        boolean flag = true;
        for(int i=0; i<n.length; i++)
        {
            if(n[i] == 1 || n[i] == 3)
            {
                flag = false;
                break;
            }

        }
        return flag;
    }
    public boolean hasBad(String str)
    {
        if(str.length()>2 && str.substring(0,3).equals("bad"))
        {
            return true;
        }
        else if (str.length()>3 && str.substring(1,4).equals("bad")) {
            return true;
        }
        else
        {
            return false;
        }
    }

}

