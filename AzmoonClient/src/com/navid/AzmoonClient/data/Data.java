package com.navid.AzmoonClient.data;

public class Data
{
    private static long user_id;
    private static long cat_id;
    private static String username;
    private static String password;
    private static String search_phrase;
    private static String category_name;

    public static long getUser_id()
    {
        return user_id;
    }

    public static void setUser_id(long user_id)
    {
        Data.user_id = user_id;
    }

    public static long getCat_id()
    {
        return cat_id;
    }

    public static void setCat_id(long cat_id)
    {
        Data.cat_id = cat_id;
    }

    public static String getUsername()
    {
        return username;
    }

    public static void setUsername(String username)
    {
        Data.username = username;
    }

    public static String getPassword()
    {
        return password;
    }

    public static void setPassword(String password)
    {
        Data.password = password;
    }

    public static String getSearch_phrase()
    {
        return search_phrase;
    }

    public static void setSearch_phrase(String search_phrase)
    {
        Data.search_phrase = search_phrase;
    }

    public static String getCategory_name()
    {
        return category_name;
    }

    public static void setCategory_name(String category_name)
    {
        Data.category_name = category_name;
    }
}
