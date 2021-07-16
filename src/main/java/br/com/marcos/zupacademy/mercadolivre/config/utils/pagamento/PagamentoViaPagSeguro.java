package br.com.marcos.zupacademy.mercadolivre.config.utils.pagamento;

public class PagamentoViaPagSeguro implements GatewayDePagamento {
    @Override
    public String redirecionamento(Long idDaCompra, Long idProduto) {
        return "pagseguro.com?returnId="+ idDaCompra +"&redirectUrl=produtos/"+idProduto+"/detalhes";
    }
}
