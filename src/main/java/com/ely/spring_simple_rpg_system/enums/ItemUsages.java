    package com.ely.spring_simple_rpg_system.enums;

    import com.ely.spring_simple_rpg_system.entity.Item;
    import com.ely.spring_simple_rpg_system.entity.Player;

    public enum ItemUsages {
        RECOVER_HP {
            @Override
            public void useItem(Player player, Item item) {
                long newHp = player.getPlayerCurrentHp() + item.getEffectValue();
                player.setPlayerCurrentHp(Math.min(newHp, player.getPlayerMaxHp()));
            }
        },
        INCREASE_AP{
            @Override
            public void useItem(Player player, Item item) {
                player.setPlayerAttackPower(player.getPlayerAttackPower() + item.getEffectValue());
            }
        },
        INCREASE_DP{
            @Override
            public void useItem(Player player, Item item) {
                player.setPlayerDefensePower(player.getPlayerDefensePower() + item.getEffectValue());
            }
        },
        INCREASE_XP{
            @Override
            public void useItem(Player player, Item item) {
                player.setPlayerXp(player.getPlayerXp() + item.getEffectValue());
            }
        },
        INCREASE_LEVEL{
            @Override
            public void useItem(Player player, Item item) {
                player.setPlayerLevel(player.getPlayerLevel() + item.getEffectValue());
            }
        };

        public abstract void useItem(Player player, Item item);
    }
