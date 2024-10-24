/*
5 classes, 1 abstrata, metodo e classe, cada class 10 atributos e 10 metodos
 */

import java.util.ArrayList;
import java.util.Random;
import  javax.swing.*;

abstract class existencia{

    private String nome;
    private int nivel;
    private int experiencia;
    public int vida = 100;
    private int stamina;
    private int forca;
    private int defesa;
    private String raca;

    public existencia(String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        this.nome = nome;
        this.nivel = nivel;
        this.experiencia = experiencia;
        this.vida = vida;
        this.stamina = stamina;
        this.forca = forca;
        this.defesa = defesa;
        this.raca = raca;

    }

}



class golpe{

    private String nome;
    public int dano;

    public golpe(String nome, int dano){
        this.nome = nome;
        this.dano = dano;
    }
}

class personagem extends existencia {

    public personagem(String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca) {
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);

    }

    public void atacar() {
        System.out.println("atacou");
    }

    public void mostrarvida(){
        System.out.println(this.vida);
    }

    public void defender() {

        System.out.println("defendeu");
    }

}


abstract class inimigo extends existencia{
    public inimigo (String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);

    }
    public abstract void atacar(personagem p);
}
class pirata extends inimigo{

    Random aleatorio = new Random();
    private ArrayList<golpe> golpes;


    public pirata (String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);
        this.golpes = new ArrayList<>();
    }

    public void addGolpe(golpe g){
        this.golpes.add(g);
    }

    @Override
    public void atacar(personagem p){
        int teste = aleatorio.nextInt(golpes.size());
        golpe sla = golpes.get(teste);
        p.vida -= sla.dano;
    }
}









class Marinheiro extends inimigo{
    Random aleatorio = new Random();
    private ArrayList<golpe> golpes;
    public Marinheiro (String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);
        this.golpes = new ArrayList<>();
    }

    public void addGolpe(golpe g){

        this.golpes.add(g);
    }

    @Override
    public void atacar(personagem p){
        int golpe  = aleatorio.nextInt(golpes.size());
        golpe aleatorio = golpes.get(golpe);
        System.out.println(aleatorio + "golpeou");
    }
}

public class onepiece {
    public static void main(String[] args) {

        personagem ps1 = new personagem("luffy",1,0,100,100,10,1,"humano");
        pirata p1 = new pirata("negra barba",1,10,100,100,10,1,"humano");
        p1.addGolpe(new golpe("batata",10));

        ps1.mostrarvida();
        p1.atacar(ps1);
        ps1.mostrarvida();




    }
}

// buenos dias (:()} 0u0