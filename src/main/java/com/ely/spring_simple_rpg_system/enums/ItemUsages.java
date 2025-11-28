    package com.ely.spring_simple_rpg_system.enums;

    import com.ely.spring_simple_rpg_system.entity.Item;
    import com.ely.spring_simple_rpg_system.entity.Player;

    public enum ItemUsages {
        RECOVER_HP {
            @Override
            public void useItem(Player player, Item item) {
                long newHp = player.getCurrentHp() + item.getEffectValue();
                player.setCurrentHp(Math.min(newHp, player.getMaxHp()));
            }
        },
        INCREASE_AP{
            @Override
            public void useItem(Player player, Item item) {
                player.setAttackPower(player.getAttackPower() + item.getEffectValue());
            }
        },
        INCREASE_DP{
            @Override
            public void useItem(Player player, Item item) {
                player.setDefensePower(player.getDefensePower() + item.getEffectValue());
            }
        },
        INCREASE_XP{
            @Override
            public void useItem(Player player, Item item) {
                player.setXp(player.getXp() + item.getEffectValue());
            }
        },
        INCREASE_LEVEL{
            @Override
            public void useItem(Player player, Item item) {
                player.setLevel(player.getLevel() + item.getEffectValue());
            }
        };

        public abstract void useItem(Player player, Item item);
    }
