package com.br.tomei.util;

import com.br.tomei.model.Breja;

public class SessionRepository
{
    static Breja i;

    public SessionRepository(){}

    public SessionRepository(Breja i)
    {
        this.i = i;
    }

    public Breja getItem()
    {
        return this.i;
    }

    public void setItem(Breja i) {this.i = i;}
}
