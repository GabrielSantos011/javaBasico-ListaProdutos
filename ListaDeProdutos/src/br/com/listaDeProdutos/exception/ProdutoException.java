package br.com.listaDeProdutos.exception;

public class ProdutoException extends Exception {
    //Exception - checked
    //RuntimeException - unchecked

    public ProdutoException(String mensagem) {
        super(mensagem);
    }

}
