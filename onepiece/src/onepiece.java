import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import  javax.swing.*;
import  java.io.BufferedReader;
import  java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

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

    public boolean ganharDegesa(int d){
        this.defesa = d;
        return true;
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
            float danorecebido =Math.abs( causardano(20) - (causardano(20) * (i.getDefesa() /10)));
            i.vida -= danorecebido;
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
        if (golpe.getNome() == "Kurouzu"){
            this.ganharDegesa(10);
        }
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

class Tela extends JPanel {
    private JFrame frame;
    private int vidaInimigo = 100;
    private int energiaInimigo = 100;
    private int vidaAliado = 100;
    private int energiaAliado = 100;
    private boolean turnoDoAliado = true; // Variável para controlar de quem é o turno

    private JButton golpe1;
    private JButton golpe2;
    private JButton golpe3;
    private pirata barbarnegra = new pirata("negra barba",1,10,100,100,10,1,"humano");
    private  Luffy luffy =  new Luffy("luffy",1,0,100,100,10,1,"humano");
    private  inimigo luta = barbarnegra;

    public Tela(String nome) {
        // Configurações do frame
        frame = new JFrame(nome);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(this);
        frame.setVisible(true);

        // Configurações dos botões de ataques
        golpe1 = new JButton("Gomo Pistol");
        golpe2 = new JButton("Gomo Gatiling");
        golpe3 = new JButton("Gomo Rifle");

        // Personalização dos botões
        golpe1.setBounds(50, 450, 150, 50);
        golpe2.setBounds(250, 450, 150, 50);
        golpe3.setBounds(450, 450, 150, 50);

        golpe1.setBackground(Color.ORANGE);
        golpe2.setBackground(Color.YELLOW);
        golpe3.setBackground(Color.PINK);
        golpe1.setForeground(Color.BLACK);
        golpe2.setForeground(Color.BLACK);
        golpe3.setForeground(Color.BLACK);
        Font buttonFont = new Font("Arial", Font.BOLD, 12);
        golpe1.setFont(buttonFont);
        golpe2.setFont(buttonFont);
        golpe3.setFont(buttonFont);

        // Listeners para botões de ataque

        golpe1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                luffy.gomo_pistol(luta);
                repaint();
            }
        });

        golpe2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarAtaque(30);
            }
        });

        golpe3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarAtaque(40);
            }
        });

        // Adiciona os botões ao painel
        this.setLayout(null);
        add(golpe1);
        add(golpe2);
        add(golpe3);

        // Atualiza os botões no início
        atualizarBotoes();
    }

    // Método para simular um ataque do aliado, reduzindo a vida do inimigo e a energia do aliado
    private void realizarAtaque(int dano) {
        if (turnoDoAliado) {
            vidaInimigo -= dano;
            if (vidaInimigo < 0) vidaInimigo = 0;
            energiaAliado -= 10;
            if (energiaAliado < 0) energiaAliado = 0;
            turnoDoAliado = false; // Alterna o turno para o inimigo
            atualizarBotoes();
            repaint();
            // Inicia o ataque do inimigo após um pequeno atraso para simular o turno
            Timer timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    atacarAliado(15);
                }
            });
            timer.setRepeats(false);
            timer.start();
        }
    }

    // Método para o ataque do inimigo
    private void atacarAliado(int dano) {
        if (!turnoDoAliado) {
            vidaAliado -= dano;
            if (vidaAliado < 0) vidaAliado = 0;
            turnoDoAliado = true; // Alterna o turno de volta para o aliado
            atualizarBotoes();
            repaint();
        }
    }

    // Método para habilitar/desabilitar botões com base no turno
    private void atualizarBotoes() {
        golpe1.setEnabled(turnoDoAliado);
        golpe2.setEnabled(turnoDoAliado);
        golpe3.setEnabled(turnoDoAliado);
    }

    // Método para desenhar a interface
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Novo fundo da tela
        g.setColor(new Color(60, 60, 120)); // Azul escuro
        g.fillRect(0, 0, getWidth(), getHeight());

        // Barra de vida do aliado (agora à esquerda)
        g.setColor(Color.GREEN);
        g.fillRect(50, 50, vidaAliado * 2, 20);
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 200, 20);
        g.drawString("Vida do Aliado: " + vidaAliado, 60, 65);

        // Barra de energia do aliado
        g.setColor(Color.ORANGE);
        g.fillRect(50, 80, energiaAliado * 2, 20);
        g.setColor(Color.BLACK);
        g.drawRect(50, 80, 200, 20);
        g.drawString("Energia do Aliado: " + energiaAliado, 60, 95);

        // Barra de vida do inimigo
        g.setColor(Color.RED);
        g.fillRect(550, 50, vidaInimigo * 2, 20);
        g.setColor(Color.BLACK);
        g.drawRect(550, 50, 200, 20);
        g.drawString("Vida do Inimigo: " + barbarnegra.vida, 560, 65);

        // Barra de energia do inimigo
        g.setColor(Color.BLUE);
        g.fillRect(550, 80, energiaInimigo * 2, 20);
        g.setColor(Color.BLACK);
        g.drawRect(550, 80, 200, 20);
        g.drawString("Energia do Inimigo: " + energiaInimigo, 560, 95);

        // Desenho dos mini personagens
        g.setColor(Color.ORANGE);
        g.fillOval(600, 300, 50, 50); // Mini personagem 1
        g.setColor(Color.GREEN);
        g.fillOval(700, 300, 50, 50); // Mini personagem 2
    }
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

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File ("C:/Users/vinig/IdeaProjects/pjbl-one-piece1/onepiece/Teste.txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            System.out.println(scan.nextLine());

        }


        Tela tela = new Tela("RPG one piece");
        // ainda tem que pensar como fazer o sistema de ganhar stamina
        Zoro zoro = new Zoro("Zoro",1,0,100,100,10,50,"humano");
        Luffy luffy = new Luffy("luffy",1,0,100,100,10,1,"humano");
        pirata barbanegra = new pirata("negra barba",1,10,100,100,10,1,"humano");

        barbanegra.addGolpe(new golpe("escuridão",50,60));
        barbanegra.addGolpe(new golpe("corte negro",40,35));
        barbanegra.addGolpe(new golpe("soco negro",20,10));
        barbanegra.addGolpe(new golpe("Kurouzu", 0, 30));

        barbanegra.atacar(luffy);
        barbanegra.mostravida();
        luffy.gomo_pistol(barbanegra);
        barbanegra.mostravida();

    }
}