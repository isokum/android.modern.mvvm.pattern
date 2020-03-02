package net.sokum.mordern.app

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class AppImageLoaderModule : AppGlideModule() {
    override fun applyOptions(
        context: Context,
        builder: GlideBuilder
    ) {
        super.applyOptions(context, builder)

        val diskCacheSizeBytes = 1024 * 1024 * 300 // 300 MB
        builder.setDiskCache(InternalCacheDiskCacheFactory(context, diskCacheSizeBytes.toLong()))
        builder.setDefaultRequestOptions(RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
    }
}