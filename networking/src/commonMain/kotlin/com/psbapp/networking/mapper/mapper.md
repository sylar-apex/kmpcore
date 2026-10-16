# Package: `networking/mapper`

DTO → domain conversion, kept out of the repository body.

## `Mapper<Domain, Dto, Entity>`

Abstract base each feature extends:

| member | notes |
|---|---|
| `dtoToDomain(dto)` / `domainToDto(domain)` | required |
| `dtoToDomain(dtoList)` / `domainToDto(domainList)` | list variants, exception-safe |
| `entityToDomain` / `domainToEntity` / `entityToDto` / `dtoToEntity` | optional, for DB entities |

```kotlin
class RechargeMapper : Mapper<RechargeProvidersData, BaseResponse<List<ProviderDto>>, Unit>() {
    override fun dtoToDomain(dto: BaseResponse<List<ProviderDto>>) = RechargeProvidersData(…)
    override fun domainToDto(domain: RechargeProvidersData) = TODO("Not yet implemented")
}
```

## Flow mapping

| function | notes |
|---|---|
| `Flow<NetworkState<T>>.mapApiData { }` | maps the success payload, keeps loading/error |
| `Flow<NetworkState<T>>.mapApiDataSync { }` | `suspend`, returns a single `NetworkState<R>` |

```kotlin
override suspend fun getProviders(request: …) =
    apiService.getProviders(request).mapApiData { mapper.dtoToDomain(it) }
```

**Gotcha:** the list overload of `dtoToDomain` swallows exceptions and returns an empty list — a
malformed item silently disappears.

---

## See also

- [`networking/response`](../response/response.md), [`networking/client`](../client/client.md)
