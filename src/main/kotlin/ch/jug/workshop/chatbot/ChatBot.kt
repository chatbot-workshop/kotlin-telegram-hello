/**
 * Kotlin Telegram Hello Bot - A simple example to post a message to Telegram
 * Copyright (C) 2018 Marcus Fihlon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package ch.jug.workshop.chatbot

import org.telegram.telegrambots.api.methods.send.SendMessage
import org.telegram.telegrambots.api.objects.Message
import org.telegram.telegrambots.api.objects.Update
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import java.util.*

class ChatBot : TelegramLongPollingBot() {

    private var botUsername = ""
    private var botToken = ""

    init {
        loadConfig()
    }

    private fun loadConfig() {
        val stream = Thread.currentThread().contextClassLoader.getResourceAsStream("bot.conf")
        val conf = Properties()
        conf.load(stream)
        botUsername = conf.getProperty("botUsername")
        botToken = conf.getProperty("botToken")
    }

    override fun getBotUsername() = botUsername

    override fun getBotToken() = botToken

    override fun onUpdateReceived(update: Update?) {
        val message = update!!.message
        sayHello(message)
    }

    fun sayHello(message: Message) {
        val chat = message.chat
        val firstName = chat!!.firstName
        val response = SendMessage()
        response.setChatId(message.chatId)
        response.text = "Hello, ${firstName}."
        execute(response)
    }

}
