package br.com.marcos.zupacademy.mercadolivre.compra.modelo;

import br.com.marcos.zupacademy.mercadolivre.config.utils.pagamento.GatewayDePagamento;
import br.com.marcos.zupacademy.mercadolivre.config.utils.pagamento.PagamentoViaPagSeguro;
import br.com.marcos.zupacademy.mercadolivre.config.utils.pagamento.PagamentoViaPayPal;

public enum FormaDePagamento {

    PAYPAL(new PagamentoViaPayPal()),
    PAGSEGURO(new PagamentoViaPagSeguro());

    private GatewayDePagamento gatewayDePagamento;

    FormaDePagamento(GatewayDePagamento gatewayDePagamento) {
        this.gatewayDePagamento = gatewayDePagamento;
    }

    public GatewayDePagamento getGatewayDePagamento() {
        return gatewayDePagamento;
    }
}
