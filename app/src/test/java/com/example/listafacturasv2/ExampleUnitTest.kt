package com.example.listafacturasv2

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.listafacturasv2.data.model.Factura
import com.example.listafacturasv2.data.repository.FacturaRepository
import com.example.listafacturasv2.domain.GetFacturasUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleUnitTest {
    @RelaxedMockK
    private lateinit var repository: FacturaRepository

    lateinit var useCase: GetFacturasUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        useCase = GetFacturasUseCase(repository)
    }

    @Test
    fun `when api return empty, get values from database`() = runBlocking {
        // Given
        coEvery { repository.getAllFacturas() } returns emptyList()

        // When
        useCase()

        // Then
        coVerify(exactly = 1) { repository.getAllFacturasRoom() }
    }

    @Test
    fun `when api return values, get it from api`() = runBlocking {
        // Given
        val list = listOf(Factura("Pagada", 50.20, "26/12/2003"))
        coEvery { repository.getAllFacturas() } returns list

        // When
        val response = useCase()

        // Then
        coVerify(exactly = 1) { repository.deleteAllRoom() }
        coVerify(exactly = 1) { repository.insertAllRoom(any()) }
        coVerify(exactly = 1) { repository.importeMaximo = any() }
        assert(list == response)
    }
}