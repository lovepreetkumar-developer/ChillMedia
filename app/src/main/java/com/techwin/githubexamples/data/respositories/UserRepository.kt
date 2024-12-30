package com.techwin.githubexamples.data.respositories

import com.techwin.githubexamples.data.network.Apis
import com.techwin.githubexamples.data.network.SafeApiRequest
import com.techwin.githubexamples.data.network.responses.GetGalleryResponse

class UserRepository(
    private val api: Apis
) : SafeApiRequest() {

    suspend fun getGallery(): GetGalleryResponse {
        val map = HashMap<String, String>()
        //map["Authorization"] = "Client-ID c56fc19601e25c5"
        return apiRequest { api.getGallery(map) }
    }

    suspend fun searchGallery(query: String): GetGalleryResponse {
        val map = HashMap<String, String>()
        map["Authorization"] = "Client-ID c56fc19601e25c5"
        return apiRequest { api.searchGallery(map, query) }
    }
}