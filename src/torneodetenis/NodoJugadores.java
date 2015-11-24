/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;

/**
 * Clase NodoEquipos
 * Esta clase representa los nodos individuales del ABB de jugadores.
 * 
 * @author Gian Franco Vitola, Elia Elias, Jose Guerrero
 * @version 2.03, 16/11/2015
 */
public class NodoJugadores implements Serializable
{
    private String _nombre;
    private int _cedula;
    private String _sexo;
    private int _edad;
    private String _equipo;
    private int _categoria;
    private int _puntos;
    private int _juegosGanados;
    private int _juegosPerdidos;
    private NodoJugadores _proximo;
    private NodoJugadores _hijoIzquierdo;
    private NodoJugadores _hijoDerecho;

    public NodoJugadores(String nombre, int cedula, String sexo, int edad, String equipo, int categoria, int puntos, int juegosGanados, int juegosPerdidos, NodoJugadores proximo)
    {
        this._nombre = nombre;
        this._cedula = cedula;
        this._sexo = sexo;
        this._edad = edad;
        this._equipo = equipo;
        this._categoria = categoria;
        this._puntos = puntos;
        this._juegosGanados = juegosGanados;
        this._juegosPerdidos = juegosPerdidos;
        this._proximo = proximo;
    }

    public NodoJugadores(String nombre, int cedula, String sexo, int edad, String equipo, int categoria, int puntos, int juegosGanados, int juegosPerdidos, NodoJugadores hijoIzquierdo, NodoJugadores hijoDerecho) 
    {
        this._nombre = nombre;
        this._cedula = cedula;
        this._sexo = sexo;
        this._edad = edad;
        this._equipo = equipo;
        this._categoria = categoria;
        this._puntos = puntos;
        this._juegosGanados = juegosGanados;
        this._juegosPerdidos = juegosPerdidos;
        this._hijoIzquierdo = hijoIzquierdo;
        this._hijoDerecho = hijoDerecho;
    }
    
    public String getNombre() 
    {
        return _nombre;
    }

    public void setNombre(String nombre) 
    {
        this._nombre = nombre;
    }

    public int getCedula() 
    {
        return _cedula;
    }

    public void setCedula(int cedula) 
    {
        this._cedula = cedula;
    }

    public String getSexo() 
    {
        return _sexo;
    }

    public void setSexo(String sexo)
    {
        this._sexo = sexo;
    }

    public int getEdad()
    {
        return _edad;
    }

    public void setEdad(int edad)
    {
        this._edad = edad;
    }

    public String getEquipo() 
    {
        return _equipo;
    }

    public void setEquipo(String equipo)
    {
        this._equipo = equipo;
    }

    public int getCategoria() 
    {
        return _categoria;
    }

    public void setCategoria(int categoria)
    {
        this._categoria = categoria;
    }

    public int getPuntos() 
    {
        return _puntos;
    }

    public void setPuntos(int puntos)
    {
        this._puntos = puntos;
    }

    public int getJuegosGanados()
    {
        return _juegosGanados;
    }

    public void setJuegosGanados(int juegosGanados)
    {
        this._juegosGanados = juegosGanados;
    }

    public int getJuegosPerdidos()
    {
        return _juegosPerdidos;
    }

    public void setJuegosPerdidos(int juegosPerdidos)
    {
        this._juegosPerdidos = juegosPerdidos;
    }

    public NodoJugadores getProximo() 
    {
        return _proximo;
    }

    public void setProximo(NodoJugadores proximo) 
    {
        this._proximo = proximo;
    }

    public NodoJugadores getHijoIzquierdo()
    {
        return _hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoJugadores hijoIzquierdo) 
    {
        this._hijoIzquierdo = hijoIzquierdo;
    }

    public NodoJugadores getHijoDerecho()
    {
        return _hijoDerecho;
    }

    public void setHijoDerecho(NodoJugadores hijoDerecho)
    {
        this._hijoDerecho = hijoDerecho;
    }
    
    static final long serialVersionUID = 8925409;
}
