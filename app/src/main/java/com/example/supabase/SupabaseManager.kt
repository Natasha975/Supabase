package com.example.supabase

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.serializer.JacksonSerializer


class SupabaseManager{
    val supabaseManager = createSupabaseClient(
        supabaseUrl = "https://kkvpucjozqqviytsovmc.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImtrdnB1Y2pvenFxdml5dHNvdm1jIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mzk3MDc1MTUsImV4cCI6MjA1NTI4MzUxNX0.qdjJLX7KYaRZUzDcfy89UgcMqMq_KtK39dbNAEMKWqI"
    ){
       // install(Auth)
        install(Postgrest)
        defaultSerializer = JacksonSerializer()
    }

    suspend fun GetMain(): List<Main>{
        return supabaseManager.from("Man").select().decodeList<Main>()
    }

    suspend fun PostMain( Name: String, Photo: String, Birthday: String){
        supabaseManager.from("Man").insert(mapOf(
            "Name" to Name,
            "Photo" to Photo,
            "Birthday" to Birthday
        ))
    }

    suspend fun UpdateMain(id: Int, Name: String, Photo: String, Birthday: String){
        supabaseManager.from("Man").update(mapOf(
            "Name" to Name,
            "Photo" to Photo,
            "Birthday" to Birthday,
            "Birthday" to Birthday
        )).eq("id", id).execute()
    }

    suspend fun DeleteMain(id: Int){
        supabaseManager.from("Man").delete().eq("id", id).execute()
    }
}
data class  Main(
    val id: Int,
    val Name: String,
    val Photo: String,
    val Birthday: String,
)