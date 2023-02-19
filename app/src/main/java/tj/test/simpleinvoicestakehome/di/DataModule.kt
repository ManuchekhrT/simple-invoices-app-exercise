package tj.test.simpleinvoicestakehome.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tj.test.simpleinvoicestakehome.data.remote.datasources.InvoicesRemoteDataSource
import tj.test.simpleinvoicestakehome.data.remote.datasources.InvoicesRemoteDataSourceImpl
import tj.test.simpleinvoicestakehome.data.remote.repository.InvoicesRepositoryImpl
import tj.test.simpleinvoicestakehome.domain.repository.InvoicesRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindInvoicesRepository(impl: InvoicesRepositoryImpl): InvoicesRepository

    @Binds
    abstract fun bindInvoicesDataSource(impl: InvoicesRemoteDataSourceImpl): InvoicesRemoteDataSource

}