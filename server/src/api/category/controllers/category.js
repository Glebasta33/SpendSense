'use strict';

/**
 * category controller
 */

const { createCoreController } = require('@strapi/strapi').factories;

module.exports = createCoreController('api::category.category',
    ({ strapi }) => ({

        //default
        async find(ctx) {
            const categories = await strapi.entityService.findMany('api::category.category', {
                filters: {
                    userId: ctx.state.user.id
                }
            })

            return categories
        },

        async create(ctx) {
            const newCategory = await strapi.service('api::category.category').create(ctx)
            const sanitizedCategory = await this.sanitizeOutput(newCategory, ctx); //удаляет небезопасные символы (рекомендуется использовать в доке)
            ctx.body = sanitizedCategory
        },

        //custom
        async sync(ctx) {
            return await strapi.service('api::category.category').sync(ctx);
        }

    })
);
