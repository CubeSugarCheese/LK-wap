package lightnovelApi.model.common

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = Gzip.GzipAsIntSerializer::class)
enum class Gzip(val num: Int) {
    Enable(1),
    Disable(0);

    object GzipAsIntSerializer: KSerializer<Gzip> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("gz", PrimitiveKind.INT)

        override fun deserialize(decoder: Decoder): Gzip {
            return when(val num = decoder.decodeInt()) {
                0 -> Disable
                1 -> Enable
                else -> throw IllegalArgumentException("invalid value of $num, must be 0 or 1")
            }
        }

        override fun serialize(encoder: Encoder, value: Gzip) {
            encoder.encodeInt(value.num)
        }

    }

}