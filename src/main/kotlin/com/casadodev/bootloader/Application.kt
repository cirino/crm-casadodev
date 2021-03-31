package com.casadodev.bootloader

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
		.args(*args)
		.packages("com.casadodev")
		.start()
}
