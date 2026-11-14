package com.envija.app.model;

public class Remessa {
    private long id;
    private String codigoRastreio;
    private String destinatario;
    private String produtoTech;       // Ex: Monitor 27", Smartphone, Placa de Vídeo
    private String numeroNFe;
    private double valorNFe;
    private double valorSeguro;       // Seguro obrigatório sobre NF-e
    private double pesoRealKg;
    private double pesoCubadoKg;
    private double valorFreteTotal;
    private boolean cargaFragil;
    private String status;            // "Em Trânsito", "Entregue (POD)", "Avaria Reportada"
    private String recebedorNome;     // Preenchido no POD
    private String recebedorDoc;      // RG/CPF do recebedor no POD
    private boolean checklistIntegro; // Integridade do lacre/embalagem na entrega

    public Remessa() {}

    public Remessa(long id, String codigoRastreio, String destinatario, String produtoTech,
                   String numeroNFe, double valorNFe, double valorSeguro, double pesoRealKg,
                   double pesoCubadoKg, double valorFreteTotal, boolean cargaFragil,
                   String status, String recebedorNome, String recebedorDoc, boolean checklistIntegro) {
        this.id = id;
        this.codigoRastreio = codigoRastreio;
        this.destinatario = destinatario;
        this.produtoTech = produtoTech;
        this.numeroNFe = numeroNFe;
        this.valorNFe = valorNFe;
        this.valorSeguro = valorSeguro;
        this.pesoRealKg = pesoRealKg;
        this.pesoCubadoKg = pesoCubadoKg;
        this.valorFreteTotal = valorFreteTotal;
        this.cargaFragil = cargaFragil;
        this.status = status;
        this.recebedorNome = recebedorNome;
        this.recebedorDoc = recebedorDoc;
        this.checklistIntegro = checklistIntegro;
    }

    // Getters e Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getCodigoRastreio() { return codigoRastreio; }
    public void setCodigoRastreio(String codigoRastreio) { this.codigoRastreio = codigoRastreio; }

    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }

    public String getProdutoTech() { return produtoTech; }
    public void setProdutoTech(String produtoTech) { this.produtoTech = produtoTech; }

    public String getNumeroNFe() { return numeroNFe; }
    public void setNumeroNFe(String numeroNFe) { this.numeroNFe = numeroNFe; }

    public double getValorNFe() { return valorNFe; }
    public void setValorNFe(double valorNFe) { this.valorNFe = valorNFe; }

    public double getValorSeguro() { return valorSeguro; }
    public void setValorSeguro(double valorSeguro) { this.valorSeguro = valorSeguro; }

    public double getPesoRealKg() { return pesoRealKg; }
    public void setPesoRealKg(double pesoRealKg) { this.pesoRealKg = pesoRealKg; }

    public double getPesoCubadoKg() { return pesoCubadoKg; }
    public void setPesoCubadoKg(double pesoCubadoKg) { this.pesoCubadoKg = pesoCubadoKg; }

    public double getValorFreteTotal() { return valorFreteTotal; }
    public void setValorFreteTotal(double valorFreteTotal) { this.valorFreteTotal = valorFreteTotal; }

    public boolean isCargaFragil() { return cargaFragil; }
    public void setCargaFragil(boolean cargaFragil) { this.cargaFragil = cargaFragil; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRecebedorNome() { return recebedorNome; }
    public void setRecebedorNome(String recebedorNome) { this.recebedorNome = recebedorNome; }

    public String getRecebedorDoc() { return recebedorDoc; }
    public void setRecebedorDoc(String recebedorDoc) { this.recebedorDoc = recebedorDoc; }

    public boolean isChecklistIntegro() { return checklistIntegro; }
    public void setChecklistIntegro(boolean checklistIntegro) { this.checklistIntegro = checklistIntegro; }
}