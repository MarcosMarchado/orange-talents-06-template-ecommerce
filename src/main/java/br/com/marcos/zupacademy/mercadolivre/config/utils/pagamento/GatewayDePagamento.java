package br.com.marcos.zupacademy.mercadolivre.config.utils.pagamento;

public interface GatewayDePagamento {
    String redirecionamento(Long idDaCompra, Long idProduto);
}
