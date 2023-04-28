package com.example.listafacturasv2.data.network

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.example.listafacturasv2.data.model.FacturaResult
import retrofit2.Response
import retrofit2.http.GET

interface FacturaService {

    @Mock
    @MockResponse(body = "{\"numFacturas\":8,\"facturas\":[{\"descEstado\":\"Pendiente de pago\",\"importeOrdenacion\":1.56,\"fecha\":\"07/02/2019\"},{\"descEstado\":\"Pagada\",\"importeOrdenacion\":25.14,\"fecha\":\"05/02/2019\"},{\"descEstado\":\"Pagada\",\"importeOrdenacion\":22.69,\"fecha\":\"08/01/2019\"},{\"descEstado\":\"Pendiente de pago\",\"importeOrdenacion\":12.84,\"fecha\":\"07/12/2018\"},{\"descEstado\":\"Pagada\",\"importeOrdenacion\":35.16,\"fecha\":\"16/11/2018\"},{\"descEstado\":\"Pagada\",\"importeOrdenacion\":18.27,\"fecha\":\"05/10/2018\"},{\"descEstado\":\"Pendiente de pago\",\"importeOrdenacion\":61.17,\"fecha\":\"05/09/2018\"},{\"descEstado\":\"Pagada\",\"importeOrdenacion\":37.18,\"fecha\":\"07/08/2018\"}]}")
    @GET("facturas")
    suspend fun listFacturas(): Response<FacturaResult>
}