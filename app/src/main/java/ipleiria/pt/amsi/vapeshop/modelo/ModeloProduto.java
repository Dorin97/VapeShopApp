package ipleiria.pt.amsi.vapeshop.modelo;

import android.graphics.drawable.Drawable;

public class ModeloProduto {
    public String nome;
    public Drawable produtoImg;
    public String descricao;
    public double preco;
    public boolean selecionado;

    public ModeloProduto(String nome, Drawable produtoImg, String descricao, double preco) {
        this.nome = nome;
        this.produtoImg = produtoImg;
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Drawable getProdutoImg() {
        return produtoImg;
    }

    public void setProdutoImg(Drawable produtoImg) {
        this.produtoImg = produtoImg;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
