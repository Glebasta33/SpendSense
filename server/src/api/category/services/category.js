'use strict';

/**
 * category service
 */

const { createCoreService } = require('@strapi/strapi').factories;

module.exports = createCoreService('api::category.category', ({ strapi }) => ({

    //custom
    async sync(ctx) {
        // Получает пользователя из контекста запроса
        // Извлекает тело запроса (ctx.request.body), которое содержит категории, отправленные клиентом.
        const user = ctx.state.user
        const { body } = ctx.request
        const type = 'api::category.category'

        // Выполняет запрос к базе данных Strapi для получения категорий, связанных с пользователем (userId: user.id).
        const categoriesFromDb = await strapi.db.query(type)
            .findMany({
                filters: {
                    userId: user.id
                }
            })

        logArray(categoriesFromDb, 'categoriesFromDb')

        // Преобразует массив категорий, которые есть на сервере, в словарь, где ключи - это UUID категорий.
        const dictCategoriesFromDb = new Map();
        categoriesFromDb.map((c) => {
            dictCategoriesFromDb.set(c.idlocal, c)
        })

        // logMap(dictCategoriesFromDb, 'dictCategoriesFromDb')

        // Фильтрует категории из тела запроса, оставляя те, которые либо отсутствуют на сервере, либо имеют более позднюю дату обновления.
        // Добавляет userId к каждой категории перед сохранением в базу данных.
        const categoriesFromClient = body.filter(category =>
            dictCategoriesFromDb[category.idlocal] ?
                dictCategoriesFromDb[category.idlocal].updatedAtLocal < category.updatedAtLocal : true
        ).map(category => {
            category.userId = user.id
            return category
        })

        logMap(categoriesFromClient, 'categoriesFromClient')

        // Фильтрует категории, которые нужно создать (те, которых ещё нет сервере).
        const categoriesToCreate = categoriesFromClient.filter(category =>
            !dictCategoriesFromDb[category.idlocal]
        )

        
        logMap(categoriesToCreate, 'categoriesToCreate')

        // Выделяет категории, которые нужно обновить (те, которые уже есть на сервере).
        const categoriesToUpdate = categoriesFromClient.filter(category =>
            dictCategoriesFromDb[category.idlocal]
        )

        
        logMap(categoriesToUpdate, 'categoriesToUpdate')

        // Если есть категории для создания, то сохраняет их в бд.
        // if (categoriesToCreate.length > 0) {
        //     await strapi.db.query(type).createMany({ data: categoriesToCreate })
        // }

        // // Если есть категории для обновления, сначала удаляет их текущие записи из базы данных, а затем создает их заново с новыми данными.
        // if (categoriesToUpdate.length > 0) {
        //     //удалить все категории с таким idlocal
        //     await strapi.db.query(type).deleteMany({
        //         where: {
        //             idlocal: {
        //                 $in: categoriesToUpdate.map(c => c.idlocal)
        //             }
        //         }
        //     })
        //     //вставить новые данные
        //     await strapi.db.query(type).createMany({
        //         data: categoriesToUpdate
        //     })
        // }

        // Преобразует массив категорий из тела запроса в словарь.
        const dictCategoriesFromClient = Object.fromEntries(body.map(c => [c.idlocal, c]))

        // Возвращает категории с сервера, которые либо отсутствуют на клиенте, либо имеют более раннюю дату обновления на сервере.
        return categoriesFromDb
            .filter(category => dictCategoriesFromClient[category.idlocal] ?
                dictCategoriesFromClient[category.idlocal].updatedAtLocal < category.updatedAtLocal : true)
    },

    // Создание категории с привязкой к id пользователя
    async create(ctx) {
        const user = ctx.state.user
        strapi.log.debug("user id = " + user.id.toString())

        const { body } = ctx.request
        const category = body.data
        category.userId = user.id

        await strapi.entityService.create('api::category.category', {
            data: category
        })

        return category
    }
}));

function logArray(cs, text) {
    cs.forEach((c) => {
        const stringC = JSON.stringify(c)
        if (c.hasOwnProperty("title")) {
            strapi.log.debug(text + ": " + c.title + " " + c.updatedAtLocal)
        }
    })
}

function logMap(cs, text) {
    cs.forEach((value, key) => {
        strapi.log.debug(text + "(key=" + key + "): " + value.title + " " + value.updatedAtLocal)
    })
}