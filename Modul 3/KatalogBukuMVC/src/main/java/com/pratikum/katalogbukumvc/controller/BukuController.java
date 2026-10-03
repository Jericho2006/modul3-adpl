/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratikum.katalogbukumvc.controller;

import com.pratikum.katalogbukumvc.model.Buku;
import com.pratikum.katalogbukumvc.model.BukuModel;
import com.pratikum.katalogbukumvc.view.BukuView;
import java.awt.event.ActionListener;

/**
 *
 * @author macbookairm12020
 */
public class BukuController {
    private final BukuModel model;
    private final BukuView view;
    
    public BukuController(BukuModel model, BukuView view){
        this.model = model;
        this.view = view;
        
        //Menggunakan lambda function untuk mengirim parameter berupa objek
        view.addSimpanListener(event -> simpanBuku());
        view.addHapusListener(event -> hapusBuku());
        view.addClearListener(event -> clearBuku());
        
        view.tampilkanData(model.getSemuaBuku());
        
        //menggunakan anonymus class
//        view.addHapusListener(new ActionListener(){
//            public void actionPerformed(ActionEvent event){
//                hapusBuku();
//            }
//            
//        });
        
    }
    
    public void simpanBuku(){
        String judul = view.getJudul().trim();
        String penulis = view.getPenulis().trim();
        String teksTahun = view.getTahun().trim();
        
        if(judul.isEmpty() || penulis.isEmpty() || teksTahun.isEmpty()){
            view.tampilkanPeringatan(
                    "Input belum lengkap",
                    "Judul, Penulis, Tahun Terbit Wajib diisi."
            );
            return;
        }
        
        int tahunTerbit;
        try{
            tahunTerbit = Integer.parseInt(teksTahun);
        } catch (Exception e){
            view.tampilkanPeringatan(
                    "Input Tidak Valid",
                    "Tahun Terbit Harus Berupa Angka."
        
        );
            return;
        }
        model.tambahBuku(new Buku(judul, penulis, tahunTerbit));
        view.tampilkanData(model.getSemuaBuku());
        view.kosongkanForm();
        
                
    }
    
    public void hapusBuku(){
        int baris = view.getBarisTerpilih();
        
        if(baris == -1){
            view.tampilkanInfo(
                    "Hapus Buku",
                    "Pilih Dulu Baris yang akan dihapus."
            
            );
            return;
        }
        
        model.hapusBuku(baris);
        view.tampilkanData(model.getSemuaBuku());
        
    }
    
    public void clearBuku(){
        model.hapusSemua();
        view.tampilkanData(model.getSemuaBuku());
        view.kosongkanForm();
        
    }
    
}
