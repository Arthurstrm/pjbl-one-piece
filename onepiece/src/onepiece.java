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
import java.util.concurrent.*;
import java.lang.*;
import java.util.Timer;
import java.util.TimerTask;

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

    public float aumentardefesa(){
        defesa += 3;;
        if (defesa > 10){
            defesa = 10;
            return defesa;
        }
        return defesa;
    }
    public float aumentaratack(){
        return forca += 3;
    }
    public int reduzirStamina(int s){
        if (stamina < s){
            System.out.println("stamina insufisciente");
            return 0;
        } else{
            return  stamina -= s;
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

    public boolean haki(){
        aumentaratack();
        aumentardefesa();
        return true;
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
    public boolean gomo_pistol(inimigo i){
        if (reduzirStamina(20) > 0){
            float danorecebido =Math.abs( causardano(20) - (causardano(20) * (i.getDefesa() /10)));
            System.out.printf("luffy usou o gomo-gomo no pistol, causando %.0f de dano\n", danorecebido);
            i.vida -= danorecebido;
            return true;
        }else {
            System.out.println("energia insuficienete\n");
            return false;
        }
    }

    public boolean gomoGatiling(inimigo i) {
        if (reduzirStamina(40) > 0) {
        float danorecebido = Math.abs(causardano(40) - (causardano(40) * (i.getDefesa() / 10)));
            System.out.printf("luffy usou gomo-gomo no GATILING, causando %.0f de dano\n", danorecebido);
        i.vida -= danorecebido;
        return true;
        }else {
            System.out.println("energia insuficienete\n");
            return false;
        }
    }

    public boolean gomoRifle(inimigo i){
        if (reduzirStamina(70) > 0) {
            float danorecebido = Math.abs(causardano(70) - (causardano(70) * (i.getDefesa() / 10)));
            System.out.printf("Luffy usou gomo-gomo no rifle causando, %.0f de dano\n", danorecebido);
            i.vida -= danorecebido;
            return true;
        }else {
            System.out.println("energia insuficienete\n");
            return false;
        }
    }
}

class Zoro extends personagem{
    private ArrayList<golpe> golpesZoro;

    public Zoro(String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca){
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);
    }

    public float causardano(int dano){
        return dano + (dano * (GetForca() / 10));
    }

    public void Asura(inimigo i){
        if (reduzirStamina(20) > 0 ){
            float danorecebido = Math.abs(causardano(30) - (causardano(30) * (i.getDefesa() / 10)));
            System.out.printf("Golpe Ashura usado, causando %.0f de dano", danorecebido);
            i.vida -= danorecebido;
        }else {
            System.out.println("energia insuficienete");
        }

    }

    public void Ul_TorGari(inimigo i){
        if (reduzirStamina(20) > 0 ){
            float danorecebido = Math.abs(causardano(40) - (causardano(40) * (i.getDefesa() / 10)));
            System.out.printf("Golpe Ul-Tora Gari usado, causando %.0f de dano", danorecebido);
            i.vida -= danorecebido;
        }else {
            System.out.println("energia insuficienete");
        }
    }

    public void shishi_sonson(inimigo i){
        if (reduzirStamina(20) > 0 ){
            float danorecebido = Math.abs(causardano(70) - (causardano(47) * (i.getDefesa() / 10)));
            System.out.printf("shishi sonson, causando %.0f de dano", danorecebido);
            i.vida -= danorecebido;
        }else {
            System.out.println("energia insuficienete");
        }
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
class pirata extends inimigo {

    Random aleatorio = new Random();
    private ArrayList<golpe> golpes;


    public pirata(String nome, int nivel, int experiencia, int vida, int stamina, int forca, int defesa, String raca) {
        super(nome, nivel, experiencia, vida, stamina, forca, defesa, raca);
        this.golpes = new ArrayList<>();
    }

    public void mostravida() {
        System.out.printf("Vida atual do inimigo: %d\n", this.vida);
    }

    public void addGolpe(golpe g) {
        this.golpes.add(g);
    }

    @Override
    public void atacar(personagem p) {
        int teste = aleatorio.nextInt(golpes.size());
        golpe golpe = golpes.get(teste);
        float danorecebido = Math.abs(golpe.dano - (golpe.dano * (p.getDefesa() / 10)));

        if (this.stamina >= golpe.energia) {
            p.vida -= danorecebido;
            this.stamina -= golpe.getEnergia();
            System.out.printf("%s usou o golpe %s, causando %.0f de dano\n", this.getNome(), golpe.getNome(), danorecebido);
        } else {
            System.out.printf("%s socou %s, causando 5 de dano\n", this.getNome(), p.getNome());
            p.vida -= 10;
        }
    }
}
class Tela extends JPanel {
    private JFrame frame;

    private JButton golpe1;
    private JButton golpe2;
    private JButton golpe3;
    private JButton ataques;
    private JButton espercial;
    private JButton haki;
    private pirata barbarnegra = new pirata("barba negra",1,10,600,200,10,5,"humano");
    private  Luffy luffy =  new Luffy("luffy",1,0,400,200,7,4,"humano");
    private String sla = "nao";
    private  inimigo luta = barbarnegra;
    private int verificalHaki = 0;
    private int verificarDefesa = 0;

    protected void mostraratack(){
        golpe1.setVisible(true);
        golpe2.setVisible(true);
        golpe3.setVisible(true);
    }

    protected void esconderatack(){
        golpe1.setVisible(false);
        golpe2.setVisible(false);
        golpe3.setVisible(false);
    }

    protected void verificarenergia(){
        if (luffy.stamina > 200){
            luffy.stamina = 200;
        }
        if (luta.stamina > 200){
            luta.stamina = 200;
        }
    }

    public void verificarvida(){
        if (luta.vida <= 0){
            luta.vida = 0;
        }
        if (luffy.vida <= 0){
            luffy.vida = 0;
        }
    }

    public Tela(String nome) {
        // Configurações do frame
        barbarnegra.addGolpe(new golpe("escuridão",50,60));
        barbarnegra.addGolpe(new golpe("corte negro",40,35));
        barbarnegra.addGolpe(new golpe("soco negro",20,10));
        barbarnegra.addGolpe(new golpe("atirar",20,0));


        frame = new JFrame(nome);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(this);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        // Configurações dos botões de ataques
        golpe1 = new JButton("Gomo Pistol");
        golpe1.setVisible(false);
        golpe2 = new JButton("Gomo Gatiling");
        golpe2.setVisible(false);
        golpe3 = new JButton("Gomo Rifle");
        golpe3.setVisible(false);
        ataques = new JButton("Atacar");
        espercial = new JButton("Especial");
        haki = new JButton("Haki");
        // Personalização dos botões

        haki.setBounds(550, 475, 150, 50);
        espercial.setBounds(314, 475, 150, 50);
        ataques.setBounds(75, 475, 150, 50);
        golpe1.setBounds(75, 475, 150, 50);
        golpe2.setBounds(314, 475, 150, 50);
        golpe3.setBounds(550, 475, 150, 50);

        haki.setBackground(Color.gray);
        haki.setForeground(Color.BLACK);
        golpe1.setBackground(Color.GRAY);
        golpe2.setBackground(Color.GRAY);
        golpe3.setBackground(Color.GRAY);
        ataques.setBackground(Color.GRAY);
        espercial.setBackground(Color.GRAY);
        espercial.setForeground(Color.BLACK);
        ataques.setForeground(Color.BLACK);
        golpe1.setForeground(Color.BLACK);
        golpe2.setForeground(Color.BLACK);
        golpe3.setForeground(Color.BLACK);

        Font buttonFont = new Font("Arial Black", Font.ITALIC, 12);
        golpe1.setFont(buttonFont);
        golpe2.setFont(buttonFont);
        golpe3.setFont(buttonFont);
        ataques.setFont(buttonFont);
        espercial.setFont(buttonFont);
        haki.setFont(buttonFont);


        ataques.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luffy.vida >1){
                    if (luta.vida >= 0) {
                        float danorecebido = Math.abs(luffy.causardano(30) - (luffy.causardano(30) * (luta.getDefesa() / 10)));
                        luta.vida -= danorecebido;
                        verificarvida();
                        repaint();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (luta.vida >= 1) {
                                    luta.atacar(luffy);
                                    verificarvida();
                                    repaint();
                                    luffy.stamina += 20;
                                    luta.stamina += 20;
                                    verificarenergia();
                                }
                            }
                        }, 200);
                        verificarvida();
                    }
                }
                verificarvida();
            }
        });
        // Listeners para botões de ataque
        espercial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            mostraratack();
            repaint();
            }
        });

        golpe1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luffy.vida >1){
                    if (luta.vida >= 0){
                        if (luffy.gomo_pistol(luta) == true){
                            animecaopistol();
                            verificarvida();
                            repaint();

                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    if (luta.vida >= 1){
                                    luta.atacar(luffy);
                                    x= 20;
                                    repaint();
                                    luffy.stamina+= 20;
                                    luta.stamina += 20;
                                    verificarenergia();
                                    verificarvida();
                                    }
                                }
                            }, 200);
                        } else{
                        }
                        esconderatack();
                        verificarvida();
                    }
                }
            }
        });

        golpe2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luffy.vida >1) {
                    if (luffy.gomoGatiling(luta) == true) {
                        animecaopistol();
                        verificarvida();
                        repaint();
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (luta.vida >= 1) {
                                    luta.atacar(luffy);
                                    verificarvida();
                                    repaint();
                                }
                                luffy.stamina += 20;
                                luta.stamina += 20;
                                verificarenergia();
                                verificarvida();
                                x = 20;
                                repaint();
                            }
                        }, 200);
                    } else {
                        x = 20;
                        repaint();
                    }
                }
                verificarvida();
                esconderatack();
            }
        });


        haki.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luffy.vida >1){
                    if (verificalHaki == 0 && verificarDefesa == 0) {
                        luffy.haki();
                        sla = "sim";
                        repaint();
                    } else {
                        System.out.println("o haki já esta ativado");
                    }
                }
                verificalHaki += 1;
                verificarDefesa += 1;
            }
        });

        golpe3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (luffy.vida >1) {
                    if (luta.vida >= 1) {
                        if (luffy.gomoRifle(luta) == true) {

                            verificarvida();
                            repaint();
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    if (luta.vida >= 1) {
                                        luta.atacar(luffy);
                                        verificarvida();
                                        repaint();
                                    }
                                    luffy.stamina += 20;
                                    luta.stamina += 20;
                                    verificarenergia();
                                    verificarvida();
                                    x = 20;
                                    repaint();
                                }
                            }, 200);
                        }
                        repaint();
                    }
                }
                verificarvida();
                esconderatack();
            }
        });

        // Adiciona os botões ao painel
        this.setLayout(null);
        add(golpe1);
        add(golpe2);
        add(golpe3);
        add(ataques);
        add(espercial);
        add(haki);
    }

    private int x = 20;
    private int y = 0;
    int j = 0;
    protected void animacaogatiling(){
        animecaopistol();
        repaint();
        testes();
        repaint();
    }

    protected void testes(){
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                x= 20;
                repaint();
            }
        }, 200);  // Atraso de 1 segundo para simular o turno do inimigo
    }
    protected void animecaopistol(){
        x= 600;

    }
    protected void voltaranimicao(){
        x= 20;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    // Método para desenhar a interface
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font infoFont = new Font("Arial", Font.BOLD, 13);
        setBackground(Color.lightGray);
        // Barra de vida do aliado (agora à esquerda)
        g.setFont(infoFont);
        g.setColor(Color.red);
        g.fillRect(50, 50, luffy.vida /2, 20);
        g.setColor(Color.black);
        g.drawRect(50, 50, 200, 20);

        g.drawString("Vida do Luffy: " + luffy.vida, 60, 65);
        // Barra de energia do aliado
        g.setColor(Color.cyan);
        g.fillRect(50, 80, luffy.getStamina() , 20);
        g.setColor(Color.black);
        g.drawRect(50, 80, 200, 20);
        g.drawString("Energia do Luffy: " + luffy.getStamina(), 60, 95);

        // Barra de vida do inimigo
        g.setColor(Color.RED);
        g.fillRect(550, 50, luta.vida / 3, 20);
        g.setColor(Color.BLACK);
        g.drawRect(550, 50, 200, 20);
        g.drawString("Vida do Inimigo: " + luta.vida, 560, 65);

        // Barra de energia do inimigo
        g.setColor(Color.cyan);
        g.fillRect(550, 80, luta.stamina, 20);
        g.setColor(Color.BLACK);
        g.drawRect(550, 80, 200, 20);
        g.drawString("Energia do Inimigo: " + luta.stamina, 560, 95);

        // Desenho dos mini personagens
        //luffy
        g.setColor(Color.pink);
        g.fillRect(50,400,20,30);
        g.fillRect(80,400,20,30);
        g.fillRect(60,325,30,50);
        if (sla == "sim") {
            g.setColor(Color.black);}  // Quando a vida do Luffy estiver baixa, será vermelha}
        g.fillRect(30,350,20,30);
        g.fillRect(100,360,x,20);

        g.setColor(Color.orange);
        g.fillOval(55, 308, 40, 30);
        g.fillOval(50, 320, 50, 15);
        g.setColor(Color.red);
        g.fillRect(50,350,50,50);
        g.fillRect(30,350,20,10);
        g.fillRect(100,350,20,10);
        g.setColor(Color.blue);
        g.fillRect(50,400,50,10);
        g.fillRect(50,400,20,15);
        g.fillRect(80,400,20,15);
        g.setColor(Color.pink);
        g.fillRect(60,325,30,20);
        g.setColor(Color.black);
        g.fillOval(65,330,5,5);
        g.fillOval(85,330,5,5);
        g.setColor(Color.red);
        g.fillRect(55,320,40,5);


        // barba negra
        g.setColor(Color.gray);
        g.fillRect(0,430,900,20);
        g.setColor(Color.black);
        g.fillOval(599,300,50,50);
        g.setColor(Color.pink);
        g.fillRect(600,400,20,30);
        g.fillRect(630,400,20,30);
        g.fillRect(580,350,20,30);
        g.fillRect(650,360,20,20);
        g.fillRect(610,325,30,50); // corpo
        g.setColor(Color.red);
        g.fillRect(600,350,50,50);
        g.fillRect(580,350,20,10);
        g.fillRect(650,350,20,10);
        g.setColor(Color.DARK_GRAY);
        g.fillRect(600,400,50,10);
        g.fillRect(600,400,20,25);
        g.fillRect(630,400,20,25);
        g.setColor(Color.black);
        g.fillRect(600,425,20,5);
        g.fillRect(630,425,20,5);
        g.setColor(Color.orange);
        g.fillRect(600,400,50,5);
        g.setColor(Color.pink);
        g.fillRect(610,325,30,20);
        g.setColor(Color.black);
        g.fillOval(610,330,5,5);
        g.fillOval(630,330,5,5);
        g.setColor(Color.yellow);
        g.fillRect(580,350,20,10);
        g.fillRect(650,350,20,10);
        g.setColor(Color.BLACK);
        g.fillRect(600,350,10,50);
        g.fillRect(640,350,10,50);
        g.fillRect(580,360,20,5);
        g.fillRect(650,360,20,5);
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
        File file = new File ("C:/Users/lg680/POO/pjbl-one-piece10/onepiece/Teste.txt");
        Scanner scan = new Scanner(file);
        while(scan.hasNextLine()){
            System.out.println(scan.nextLine());

        }


        Tela tela = new Tela("RPG one piece");
        /*
        // ainda tem que pensar como fazer o sistema de ganhar stamina
        Zoro zoro = new Zoro("Zoro",1,0,100,100,10,50,"humano");
        Luffy luffy = new Luffy("luffy",1,0,100,100,10,1,"humano");
        pirata barbanegra = new pirata("negra barba",1,10,100,100,10,1,"humano");

        barbanegra.addGolpe(new golpe("escuridão",50,60));
        barbanegra.addGolpe(new golpe("corte negro",40,35));
        barbanegra.addGolpe(new golpe("soco negro",20,10));

        barbanegra.atacar(luffy);
        barbanegra.mostravida();
        luffy.gomo_pistol(barbanegra);
        barbanegra.mostravida();*/

    }
}