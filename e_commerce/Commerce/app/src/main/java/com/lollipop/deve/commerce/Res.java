package com.lollipop.deve.commerce;

import com.lollipop.deve.commerce.Models.Clients;

public class Res {

    public static final String URL ="http://192.168.203.2:8084/WebApplication2/webresources";
    public static String username="ilyass07";
    public static String host="drging.heliohost.org";
    public static int id=1;
    public  static Clients clients=null;
    public static void setClient(Clients c)
    {
       clients=c;
    }
    public static Clients getClients()
    {
        return  clients;
    }

}
