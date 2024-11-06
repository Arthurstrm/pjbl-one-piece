import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import  javax.swing.*;
import  java.io.BufferedReader;
import  java.io.FileReader;
import java.io.IOException;
import java.util.List;


abstract class existencia{

    private String nome;
    private int nivel;
    private int experiencia;
    public int vida = 100;
    public int stamina;
    private float forca;
    private float defesa;
    private String raca;
    private boolean haki = false;

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

    public float GetForca(){
        return  forca;
    }

    public boolean reduzirStamina(int s){
        if (stamina <= 0){
            System.out.println("stamina insufisciente");
            return false;
        } else{
            stamina -= s;
            return  true;
        }
    }

    public int getStamina(){   return  this.stamina;}

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
    public int energia;

    public String getNome(){
        return this.nome;
    }

    public int getEnergia(){
        return energia;
    }

    public golpe(String nome, int dano, int energia){
        this.nome = nome;
        this.dano = dano;
        this.energia = energia;
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
        System.out.printf("Vida atual: %d\n", this.vida);
    }

    public void defender() {


    }
}

//class do personagem luffy
class Luffy extends personagem{

    public Luffy(String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);
    }

    //metodo para  causas dano e almenta ele com base na força do atacante
    public float causardano(int dano){
        return dano + (dano * (GetForca() / 10));
    }

    public void mostrarStamina(){
        System.out.println(stamina);
    }

    // metodo do golpe gomo-gomo no pistol
    public void gomo_pistol(inimigo i){
        if (reduzirStamina(10) == true){
            System.out.printf("luffy usou o gomo-gomo no pistol, causando %.0f de dano\n", causardano(20));
            i.vida -= causardano(20);
        }
    }

    // AINDA VOU FAZER, confia 😎
    // SISTEMA DE HAKI, sistema onde vai gastar stamina a mais por galpe, além de ganhar dano e defesa
    // ainde tem que ser implementado

    public void gomoGatiling(inimigo i){
        System.out.println("luffy usou gomo-gomo no GATILING, causando 40 de dano\n");
        i.vida -= 40;
    }

    public void gomoRifle(inimigo i){
        System.out.println("Luffy usou gomo-gomo no rifle causando, 70 de dano\n");
        i.vida -= 70;
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
        System.out.printf("Vida atual do inimigo: %d\n", this.vida);
    }

    public void addGolpe(golpe g){
        this.golpes.add(g);
    }

    @Override
    public void atacar(personagem p){
        int teste = aleatorio.nextInt(golpes.size());
        golpe golpe = golpes.get(teste);

        //essa variavel pega o dano recebido junto com a defesa
        //❌❌❌ arrumar o dano recebido, ta dando negativo
        float danorecebido =Math.abs( golpe.dano - (golpe.dano * (p.getDefesa() /10)));

        // estou fazendo testes para gastar stamina
        if (this.stamina >= golpe.energia){
            p.vida -= danorecebido;
            this.stamina -= golpe.getEnergia();
            System.out.printf("%s usou o golpe %s, causando %.0f de dano\n", this.getNome(), golpe.getNome(), danorecebido );
        }else {
            System.out.printf("%s socou %s, causando 5 de dano\n", this.getNome(), p.getNome());
            p.vida -= 5;
        }
    }

    //só para ver se a stamina ta funcionando
    public void mostrarStamina(){
        System.out.println(stamina);
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

    public void mostravida(personagem p){
        System.out.printf("Vida atual do inimigo: %d\n", this.vida);
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

// tendando iniciar o inferno/ merda/ capeta/ capiroto/ belzebu/ diabo / bixo piruleta de interdace grafica

class tela extends  JPanel{
    private static final int ALTURA_BARRA_TITULO = 20;
    private JFrame frame;
    public tela(String nome) {

        frame = new JFrame(nome); // cria um frame
        frame.add(this); // insere o território no frame
        frame.setSize(3000, 800 + ALTURA_BARRA_TITULO ); // define as dimensões do frame
        frame.setVisible(true); // torna o frame visível
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // define como o frame é fechado
        setBackground(Color.getHSBColor(0, 100, 100));// muda a cor do fundo
    }

}

class teste extends  JFrame{
}


class Teste{
    public Teste(String teste) {
    }

    public static void lertxt(String filePath){
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(" - ");
                String nome = dados[0];
                int idade = Integer.parseInt(dados[1]);
                String poder = dados[2];

                System.out.println("nome " + nome + "Idade" + idade + " poder" + poder);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        lertxt(filePath);

    }
}

public class onepiece {
    public onepiece(String teste) {
    }

    public static void lertxt(String filePath){
        String linha;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while ((linha = br.readLine()) != null){
                String[] dados = linha.split(" - ");
                String nome = dados[0];
                int idade = Integer.parseInt(dados[1]);
                String poder = dados[2];

                System.out.println("nome " + nome + "Idade" + idade + " poder" + poder);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\lg680\\Downloads\\one piece";
        lertxt(filePath);


        tela tela = new tela("RPG one piece");
        // ainda tem que pensar como fazer o sistema de ganhar stamina
        Zoro zoro = new Zoro("Zoro",1,0,100,100,10,50,"humano");
        Luffy luffy = new Luffy("luffy",1,0,100,100,10,1,"humano");
        pirata barbanegra = new pirata("negra barba",1,10,100,100,10,1,"humano");

        barbanegra.addGolpe(new golpe("escuridão",50,60));
        barbanegra.addGolpe(new golpe("corte negro",40,35));
        barbanegra.addGolpe(new golpe("soco negro",20,10));
        barbanegra.addGolpe(new golpe("Kurouzu", 0, 30));

        luffy.gomo_pistol(barbanegra);
        barbanegra.atacar(luffy);
    }
}