package com.example.supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.serializer.JacksonSerializer

class SupabaseManager{
    val supabaseManager = createSupabaseClient(
        supabaseUrl = "https://bruncvqouukpaizbawdn.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJydW5jdnFvdXVrcGFpemJhd2RuIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MzczNjcwNjcsImV4cCI6MjA1Mjk0MzA2N30.C-PgbFsvUKn8UBT3meFtIymT4_e2FDJWP3TLgYgkdVI"
    ){
        install(Postgrest)
        defaultSerializer = JacksonSerializer()
    }

    suspend fun GetMain(): List<Main>{
        return supabaseManager.from("Per").select().decodeList<Main>()
    }

    suspend fun PostMain( Name: String, Photo: String, Birthday: String){
        supabaseManager.from("Per").insert(mapOf(
            "Name" to Name,
            "Photo" to Photo,
            "Birthday" to Birthday
        ))
}
}
data class  Main(
    val id: Int,
    val Name: String,
    val Photo: String,
    val Birthday: String,
)