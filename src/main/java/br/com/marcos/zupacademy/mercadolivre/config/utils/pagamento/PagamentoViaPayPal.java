package br.com.marcos.zupacademy.mercadolivre.config.utils.pagamento;

public class PagamentoViaPayPal implements GatewayDePagamento {
    @Override
    public String redirecionamento(Long idDaCompra, Long idProduto) {
        return "paypal.com?buyerId="+ idDaCompra +"&redirectUrl=produtos/"+idProduto+"/detalhes";
    }
}
