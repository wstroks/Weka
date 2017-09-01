/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafica;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import opencv.ExtratorImagem;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.LibSVM;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.JRip;
import weka.classifiers.rules.OneR;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author wstro
 */
public class Preditor extends javax.swing.JFrame {

    private Instances instancias;

    /**
     * Creates new form Preditor
     */
    public Preditor() {
        initComponents();
    }

    public void classificaNeuro() throws Exception {
        MultilayerPerceptron multi = new MultilayerPerceptron();
        //multi.setTrainingTime(WIDTH);
        multi.buildClassifier(instancias);

        Instance novo = new DenseInstance(instancias.numAttributes());
        novo.setDataset(instancias);
        novo.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novo.setValue(1, Float.parseFloat(lblAzulCalçaoBart.getText()));
        novo.setValue(2, Float.parseFloat(lblsapatoBart.getText()));
        novo.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novo.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novo.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));

        //previsão
        double resultado1[] = multi.distributionForInstance(novo);
        //double resultado[]= rip.distributionForInstance(novo);
        DecimalFormat df = new DecimalFormat("#,###.0000");
        neurobart.setText("Bart: " + df.format(resultado1[0]));
        neurohomer.setText("Hormer: " + df.format(resultado1[1]));

    }

    public void classificaLIsv() throws Exception {
        LibSVM lib = new LibSVM();
        lib.setKernelType(new SelectedTag(LibSVM.KERNELTYPE_LINEAR, LibSVM.TAGS_KERNELTYPE));

        lib.buildClassifier(instancias);

        Instance novo = new DenseInstance(instancias.numAttributes());
        novo.setDataset(instancias);
        novo.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novo.setValue(1, Float.parseFloat(lblAzulCalçaoBart.getText()));
        novo.setValue(2, Float.parseFloat(lblsapatoBart.getText()));
        novo.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novo.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novo.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));

        //previsão
        double resultado1[] = lib.distributionForInstance(novo);
        //double resultado[]= rip.distributionForInstance(novo);
        DecimalFormat df = new DecimalFormat("#,###.0000");
        smvbart.setText("Bart: " + df.format(resultado1[0]));
        smvhomer.setText("Hormer: " + df.format(resultado1[1]));
    }

    public void classifcaIBYKNN() throws Exception {
        IBk knn = new IBk(3);
        knn.buildClassifier(instancias);

        Instance novo = new DenseInstance(instancias.numAttributes());
        novo.setDataset(instancias);
        novo.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novo.setValue(1, Float.parseFloat(lblAzulCalçaoBart.getText()));
        novo.setValue(2, Float.parseFloat(lblsapatoBart.getText()));
        novo.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novo.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novo.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));

        //previsão
        double resultado1[] = knn.distributionForInstance(novo);
        //double resultado[]= rip.distributionForInstance(novo);
        DecimalFormat df = new DecimalFormat("#,###.0000");
        vaibart.setText("Bart: " + df.format(resultado1[0]));
        vaihomer.setText("Hormer: " + df.format(resultado1[1]));

    }

    public void classificarRegras() throws Exception {
        OneR oner = new OneR();
        JRip rip = new JRip();

        oner.buildClassifier(instancias);
        rip.buildClassifier(instancias);

        Instance novo = new DenseInstance(instancias.numAttributes());
        novo.setDataset(instancias);
        novo.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novo.setValue(1, Float.parseFloat(lblAzulCalçaoBart.getText()));
        novo.setValue(2, Float.parseFloat(lblsapatoBart.getText()));
        novo.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novo.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novo.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));

        //previsão
        double resultado1[] = oner.distributionForInstance(novo);
        //double resultado[]= rip.distributionForInstance(novo);
        DecimalFormat df = new DecimalFormat("#,###.0000");
        onebart.setText("Bart: " + df.format(resultado1[0]));
        onehomer.setText("Hormer: " + df.format(resultado1[1]));

        double resultado[] = rip.distributionForInstance(novo);
        DecimalFormat dc = new DecimalFormat("#,###.0000");
        ripbart.setText("Bart: " + dc.format(resultado[0]));
        riphomer.setText("Hormer: " + dc.format(resultado[1]));

    }

    public void classificaNaiveBayes() throws Exception {
        NaiveBayes nb = new NaiveBayes();
        //criação da tabela
        nb.buildClassifier(instancias);

        //criação de novo registro
        Instance novo = new DenseInstance(instancias.numAttributes());
        novo.setDataset(instancias);
        novo.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novo.setValue(1, Float.parseFloat(lblAzulCalçaoBart.getText()));
        novo.setValue(2, Float.parseFloat(lblsapatoBart.getText()));
        novo.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novo.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novo.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));

        //previsão
        double resultado[] = nb.distributionForInstance(novo);
        DecimalFormat df = new DecimalFormat("#,###.0000");
        lblnaivebart.setText("Bart: " + df.format(resultado[0]));
        lblnaivehomer.setText("Hormer: " + df.format(resultado[1]));

    }

    public void classificaj48() throws Exception {
        J48 arvore = new J48();
        //criar arvore
        arvore.buildClassifier(instancias);

        //criação de novo registro
        Instance novo = new DenseInstance(instancias.numAttributes());
        novo.setDataset(instancias);
        novo.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
        novo.setValue(1, Float.parseFloat(lblAzulCalçaoBart.getText()));
        novo.setValue(2, Float.parseFloat(lblsapatoBart.getText()));
        novo.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
        novo.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
        novo.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));

        //previsão
        double resultado[] = arvore.distributionForInstance(novo);
        DecimalFormat df = new DecimalFormat("#,###.0000");
        j48bart.setText("Bart: " + df.format(resultado[0]));
        j48homer.setText("Hormer: " + df.format(resultado[1]));

    }

    public void carregaWeka() throws Exception {
        DataSource a = new DataSource("src\\opencv\\caracteristicas.arff");
        instancias = a.getDataSet();
        instancias.setClassIndex(instancias.numAttributes() - 1);
        // System.out.println(instancias.toString());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSelecionarImagem = new javax.swing.JButton();
        txtCaminhoImagem = new javax.swing.JTextField();
        LblImagem = new javax.swing.JLabel();
        btnExtrairCarac = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblLaranjaBart = new javax.swing.JLabel();
        lblAzulCalçaoBart = new javax.swing.JLabel();
        lblsapatoBart = new javax.swing.JLabel();
        lblAzulHomer = new javax.swing.JLabel();
        lblMarromHomer = new javax.swing.JLabel();
        lblSapatoHomer = new javax.swing.JLabel();
        btnClassifica = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        lblnaivebart = new javax.swing.JLabel();
        lblnaivehomer = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        j48bart = new javax.swing.JLabel();
        j48homer = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        onebart = new javax.swing.JLabel();
        onehomer = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ripbart = new javax.swing.JLabel();
        riphomer = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        vaibart = new javax.swing.JLabel();
        vaihomer = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        smvbart = new javax.swing.JLabel();
        smvhomer = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        neurobart = new javax.swing.JLabel();
        neurohomer = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSelecionarImagem.setText("Selecionar Imagem");
        btnSelecionarImagem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelecionarImagemActionPerformed(evt);
            }
        });

        LblImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnExtrairCarac.setText("Extrair Caracteristicas");
        btnExtrairCarac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExtrairCaracActionPerformed(evt);
            }
        });

        jLabel1.setText("Características do Bart");

        jLabel2.setText("Características do Homer");

        lblLaranjaBart.setText("jLabel3");

        lblAzulCalçaoBart.setText("jLabel4");

        lblsapatoBart.setText("jLabel5");

        lblAzulHomer.setText("jLabel6");

        lblMarromHomer.setText("jLabel7");

        lblSapatoHomer.setText("jLabel8");

        btnClassifica.setText("Classificar");
        btnClassifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClassificaActionPerformed(evt);
            }
        });

        jLabel3.setText("Naive Bayes");

        lblnaivebart.setText("BART");

        lblnaivehomer.setText("homer");

        jLabel4.setText("Ar. Decisão J48");

        j48bart.setText("jLabel5");

        j48homer.setText("jLabel5");

        jLabel5.setText("OneR");

        onebart.setText("jLabel6");

        onehomer.setText("jLabel6");

        jLabel6.setText("JRip");

        ripbart.setText("jLabel7");

        riphomer.setText("jLabel7");

        jLabel7.setText("KNN/ibk");

        vaibart.setText("jLabel8");

        vaihomer.setText("jLabel8");

        jLabel8.setText("SMV");

        smvbart.setText("jLabel9");

        smvhomer.setText("jLabel9");

        jLabel9.setText("MultiLayer/Neuro");

        neurobart.setText("jLabel10");

        neurohomer.setText("jLabel10");

        jButton1.setText("Treinar/Gerar Modelo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Classificar usando arquivo/modelo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(btnExtrairCarac)
                                .addGap(29, 29, 29)
                                .addComponent(btnClassifica)
                                .addGap(0, 359, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblsapatoBart)
                                    .addComponent(lblnaivehomer)
                                    .addComponent(jLabel3)
                                    .addComponent(lblnaivebart)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(vaibart, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(lblLaranjaBart)
                                            .addGap(206, 206, 206))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(vaihomer)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(j48bart)
                                                .addComponent(jLabel4)
                                                .addComponent(j48homer)
                                                .addComponent(jLabel5)
                                                .addComponent(onebart)
                                                .addComponent(onehomer))))
                                    .addComponent(jLabel1)
                                    .addComponent(lblAzulCalçaoBart))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(66, 358, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblAzulHomer)
                            .addComponent(jLabel2)
                            .addComponent(lblMarromHomer)
                            .addComponent(lblSapatoHomer)
                            .addComponent(jLabel8)
                            .addComponent(smvbart)
                            .addComponent(smvhomer)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(ripbart)
                                    .addComponent(riphomer))
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(neurohomer)
                                    .addComponent(neurobart)
                                    .addComponent(jLabel9))))
                        .addGap(141, 141, 141))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSelecionarImagem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCaminhoImagem))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSelecionarImagem)
                    .addComponent(txtCaminhoImagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(LblImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addContainerGap(54, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnExtrairCarac)
                            .addComponent(btnClassifica))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLaranjaBart)
                            .addComponent(lblAzulHomer))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAzulCalçaoBart)
                            .addComponent(lblMarromHomer))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSapatoHomer)
                            .addComponent(lblsapatoBart))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblnaivebart)
                            .addComponent(j48bart)
                            .addComponent(ripbart)
                            .addComponent(neurobart))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblnaivehomer)
                            .addComponent(j48homer)
                            .addComponent(riphomer)
                            .addComponent(neurohomer))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vaibart)
                            .addComponent(onebart)
                            .addComponent(smvbart))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vaihomer)
                            .addComponent(onehomer)
                            .addComponent(smvhomer))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnSelecionarImagemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelecionarImagemActionPerformed
        // TODO add your handling code here:
        JFileChooser fc = new JFileChooser();
        int retorno = fc.showDialog(this, "Selecione a Imagem");
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File Arquivo = fc.getSelectedFile();
            txtCaminhoImagem.setText(Arquivo.getAbsolutePath());

            BufferedImage imagemBMP = null;
            try {
                imagemBMP = ImageIO.read(Arquivo);
            } catch (IOException ex) {
                Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
            }

            ImageIcon imageLabel = new ImageIcon(imagemBMP);
            LblImagem.setIcon(new ImageIcon(imageLabel.getImage().getScaledInstance(LblImagem.getWidth(), LblImagem.getHeight(),
                    Image.SCALE_DEFAULT)));
        }

    }//GEN-LAST:event_btnSelecionarImagemActionPerformed

    private void btnExtrairCaracActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtrairCaracActionPerformed
        ExtratorImagem extrator = new ExtratorImagem();
        float[] caracteristicas = extrator.extrairCaracteristicas(txtCaminhoImagem.getText());
        lblLaranjaBart.setText(String.valueOf(caracteristicas[0]));
        lblAzulCalçaoBart.setText(String.valueOf(caracteristicas[1]));
        lblsapatoBart.setText(String.valueOf(caracteristicas[2]));
        lblAzulHomer.setText(String.valueOf(caracteristicas[3]));
        lblMarromHomer.setText(String.valueOf(caracteristicas[4]));
        lblSapatoHomer.setText(String.valueOf(caracteristicas[5]));


    }//GEN-LAST:event_btnExtrairCaracActionPerformed

    private void btnClassificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClassificaActionPerformed
        try {
            // TODO add your handling code here:
            carregaWeka();
            classificaNaiveBayes();
            classificaj48();
            classificarRegras();
            classifcaIBYKNN();
            classificaLIsv();
            classificaNeuro();
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnClassificaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            carregaWeka();
            J48 j48 = new J48();
            //j48.setUnpruned(true);
            j48.buildClassifier(instancias);

            ObjectOutputStream classificador = new ObjectOutputStream(new FileOutputStream("src/opencv/arvore_treinada.model"));
            classificador.writeObject(j48);
            classificador.flush();
            classificador.close();
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int quantidadeHomer=0, quantidadeBart=0;
       final double rejeicao=0.99;
        try {
            // TODO add your handling code here:
            ObjectInputStream entradaj48 = new ObjectInputStream(new FileInputStream("src\\opencv\\J48.model"));
          
            J48 j48=(J48) entradaj48.readObject();
            entradaj48.close();
            
            ObjectInputStream entradajrip = new ObjectInputStream(new FileInputStream("src\\opencv\\Jrip.model"));
          
            JRip jrip=(JRip) entradajrip.readObject();
            entradajrip.close();
            
            
            ObjectInputStream entradaibk = new ObjectInputStream(new FileInputStream("src\\opencv\\ibk.model"));
          
            IBk ibk= (IBk) entradaibk.readObject();
            entradaibk.close();
            
            carregaWeka();
            Instance n = new DenseInstance(instancias.numAttributes());
            n.setDataset(instancias);
            n.setValue(0, Float.parseFloat(lblLaranjaBart.getText()));
            n.setValue(1, Float.parseFloat(lblAzulCalçaoBart.getText()));
            n.setValue(2, Float.parseFloat(lblsapatoBart.getText()));
            n.setValue(3, Float.parseFloat(lblMarromHomer.getText()));
            n.setValue(4, Float.parseFloat(lblAzulHomer.getText()));
            n.setValue(5, Float.parseFloat(lblSapatoHomer.getText()));

            //previsão
            double resultado[] = j48.distributionForInstance(n);
            DecimalFormat dc = new DecimalFormat("#,###.0000");
            System.out.println("J48");
            System.out.println("Bart: " + dc.format(resultado[0]));
            System.out.println("Hormer: " + dc.format(resultado[1]));
            if(resultado[0] > resultado[1]){
                if(resultado[0] > rejeicao){
                    quantidadeBart++;
                }
                
            }
            else if(resultado[1] > resultado[0]){
                if(resultado[1] > rejeicao){
                quantidadeHomer++;
                }
            }
            else{
                if(resultado[0] > rejeicao && resultado[1] > rejeicao){
                quantidadeBart++;
                quantidadeHomer++;
                
                }
            }
            
            System.out.println("Jrip");
            resultado = jrip.distributionForInstance(n);
            System.out.println("Bart: " + dc.format(resultado[0]));
            System.out.println("Hormer: " + dc.format(resultado[1]));
           if(resultado[0] > resultado[1]){
                if(resultado[0] > rejeicao){
                    quantidadeBart++;
                }
                
            }
            else if(resultado[1] > resultado[0]){
                if(resultado[1] > rejeicao){
                quantidadeHomer++;
                }
            }
            else{
                if(resultado[0] > rejeicao && resultado[1] > rejeicao){
                quantidadeBart++;
                quantidadeHomer++;
                
                }
            }
            System.out.println("IBK");
            resultado = ibk.distributionForInstance(n);
            System.out.println("Bart: " + dc.format(resultado[0]));
            System.out.println("Hormer: " + dc.format(resultado[1]));
            
          if(resultado[0] > resultado[1]){
                if(resultado[0] > rejeicao){
                    quantidadeBart++;
                }
                
            }
            else if(resultado[1] > resultado[0]){
                if(resultado[1] > rejeicao){
                quantidadeHomer++;
                }
            }
            else{
                if(resultado[0] > rejeicao && resultado[1] > rejeicao){
                quantidadeBart++;
                quantidadeHomer++;
                
                }
            }
            
            System.out.println("Quantidade Bart: " +quantidadeBart);
            System.out.println("Quantidade Homer: " +quantidadeHomer);
            
            if(quantidadeBart==0 && quantidadeHomer==0 ){
                System.out.println("A instancia foi rejeitada ");
            }else{
            if(quantidadeBart > quantidadeHomer){
                System.out.println(" Bart: " );
            }
            else {
                System.out.println("homer: " );
            }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Preditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Preditor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Preditor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LblImagem;
    private javax.swing.JButton btnClassifica;
    private javax.swing.JButton btnExtrairCarac;
    private javax.swing.JButton btnSelecionarImagem;
    private javax.swing.JLabel j48bart;
    private javax.swing.JLabel j48homer;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblAzulCalçaoBart;
    private javax.swing.JLabel lblAzulHomer;
    private javax.swing.JLabel lblLaranjaBart;
    private javax.swing.JLabel lblMarromHomer;
    private javax.swing.JLabel lblSapatoHomer;
    private javax.swing.JLabel lblnaivebart;
    private javax.swing.JLabel lblnaivehomer;
    private javax.swing.JLabel lblsapatoBart;
    private javax.swing.JLabel neurobart;
    private javax.swing.JLabel neurohomer;
    private javax.swing.JLabel onebart;
    private javax.swing.JLabel onehomer;
    private javax.swing.JLabel ripbart;
    private javax.swing.JLabel riphomer;
    private javax.swing.JLabel smvbart;
    private javax.swing.JLabel smvhomer;
    private javax.swing.JTextField txtCaminhoImagem;
    private javax.swing.JLabel vaibart;
    private javax.swing.JLabel vaihomer;
    // End of variables declaration//GEN-END:variables
}
