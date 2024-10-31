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
    private float defesa;
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
        
        switch (raca) {
            case "humano":
                vida = 100;
            case "Gigante":
                vida = 150;
            case "HomemPeixe":
                vida = 130;
            case "Mink":
                vida = 120;
            case "Cyborg":
                vida = 110;
        }
    }

    public float getDefesa(){
        return defesa;
    }

    public String getNome(){
        return this.nome;
    }
}



class golpe{

    private String nome;
    public int dano;

    public String getNome(){
        return this.nome;
    }

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


    }
}

//class do personagem luffy
class Luffy extends personagem{

    public Luffy(String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);
    }

    //Confia😎
    // vou add mais alguns ataques
    // tbm vou fazer o metodo de defesa
    // consumir energia tbm
    public void gomo_pistol(inimigo i){
        System.out.println("luffy usou o gomo-gomo no pistol, causando 20 de dano");
        i.vida -= 20;
    }

    public void gomoGatiling(inimigo i){
        System.out.println("luffy usou gomo-gomo no GATILING, causando 40 de dano");
        i.vida -= 40;
    }
}

class Zoro extends personagem{
    private ArrayList<golpe> golpesZoro;

    public Zoro(String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);
    }

    public void Asura(inimigo i){
        System.out.println("Golpe Ashura usado, causando 30 de dano");
        i.vida -= 30;
    }
    public void Ul_TorGari(inimigo i){
        System.out.println("Golpe Ul-Tora Gari usado, causando 40 de dano");
        i.vida -= 40;
    }
}

//classe inimigo
abstract class inimigo extends existencia{
    public inimigo (String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);

    }
    public abstract void atacar(personagem p);
}


//classe pirata
class pirata extends inimigo{

    Random aleatorio = new Random();
    private ArrayList<golpe> golpes;


    public pirata (String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);
        this.golpes = new ArrayList<>();
    }

    public void mostravida(){
        System.out.println(this.vida);
    }

    public void addGolpe(golpe g){
        this.golpes.add(g);
    }

    @Override
    public void atacar(personagem p){
        int teste = aleatorio.nextInt(golpes.size());
        golpe golpe = golpes.get(teste);
        float danorecebido= golpe.dano - (golpe.dano * (p.getDefesa() /10));
        p.vida -= danorecebido;

        System.out.printf("%s usou o golpe %s, causando %.0f de dano\n", this.getNome(), golpe.getNome(), danorecebido );
    }
}


//classe inimigo marinheiro
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
        System.out.printf("%s usou o golde %s, causando %d de dano\n", getNome(), aleatorio.getNome(), aleatorio.dano);
    }
}

public class onepiece {
    public static void main(String[] args) {

        // ainda tem que pensar como fazer o sistema de ganhar stamina
        Zoro zoro = new Zoro("Zoro",1,0,100,100,10,50,"humano");
        Luffy luffy = new Luffy("luffy",1,0,100,100,10,8,"humano");
        pirata p1 = new pirata("negra barba",1,10,100,100,10,1,"humano");

        p1.addGolpe(new golpe("escuiridão",10));
        p1.addGolpe(new golpe("corte negro",20));

        luffy.mostrarvida();
        p1.atacar(luffy);
        luffy.mostrarvida();
        System.out.println(" ");
        p1.mostravida();
        luffy.gomo_pistol(p1);
        p1.mostravida();



    }
}
