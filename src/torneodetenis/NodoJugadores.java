/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package torneodetenis;

import java.io.Serializable;

/**
 *
 * @author Administrador
 */
public class NodoJugadores implements Serializable
{
    private String nombre;
    private int cedula;
    private String sexo;
    private int edad;
    private String equipo;
    private int categoria;
    private int puntos;
    private NodoJugadores proximo;
    private NodoJugadores hijoIzquierdo;
    private NodoJugadores hijoDerecho;

    public NodoJugadores(String nombre, int cedula, String sexo, int edad, String equipo, int categoria, int puntos, NodoJugadores proximo)
    {
        this.nombre = nombre;
        this.cedula = cedula;
        this.sexo = sexo;
        this.edad = edad;
        this.equipo = equipo;
        this.categoria = categoria;
        this.puntos = puntos;
        this.proximo = proximo;
    }

    public NodoJugadores(String nombre, int cedula, String sexo, int edad, String equipo, int categoria, int puntos, NodoJugadores hijoIzquierdo, NodoJugadores hijoDerecho) 
    {
        this.nombre = nombre;
        this.cedula = cedula;
        this.sexo = sexo;
        this.edad = edad;
        this.equipo = equipo;
        this.categoria = categoria;
        this.puntos = puntos;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }
    
    
    
    
    public String getNombre() 
    {
        return nombre;
    }

    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    public int getCedula() 
    {
        return cedula;
    }

    public void setCedula(int cedula) 
    {
        this.cedula = cedula;
    }

    public String getSexo() 
    {
        return sexo;
    }

    public void setSexo(String sexo)
    {
        this.sexo = sexo;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    public String getEquipo() 
    {
        return equipo;
    }

    public void setEquipo(String equipo)
    {
        this.equipo = equipo;
    }

    public int getCategoria() 
    {
        return categoria;
    }

    public void setCategoria(int categoria)
    {
        this.categoria = categoria;
    }

    public int getPuntos() 
    {
        return puntos;
    }

    public void setPuntos(int puntos)
    {
        this.puntos = puntos;
    }

    public NodoJugadores getProximo() 
    {
        return proximo;
    }

    public void setProximo(NodoJugadores proximo) 
    {
        this.proximo = proximo;
    }

    public NodoJugadores getHijoIzquierdo()
    {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoJugadores hijoIzquierdo) 
    {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoJugadores getHijoDerecho()
    {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoJugadores hijoDerecho)
    {
        this.hijoDerecho = hijoDerecho;
    }
    
    public void mostrar()
    {
        System.out.println("Nombre: " + nombre + " - Cedula: " + cedula + "- Sexo: " + sexo + " - Edad: " + edad + "- Equipo: " + equipo + "- Puntos: " + puntos);
    }
    
    public void retornarJugador()
    {
        
    }
    
    static final long serialVersionUID = 8925409;
}
