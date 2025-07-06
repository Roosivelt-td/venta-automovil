#!/bin/bash

# Reemplaza 'rama_base' con el nombre de tu rama base
rama_base="main"

# Lista de ramas a actualizar (reemplaza con tus ramas)
ramas=("developer")

for rama in "${ramas[@]}"; do
  echo "Actualizando rama: $rama"
  git checkout $rama
  git merge $rama_base
  if [[ $? -eq 0 ]]; then
    echo "Rama $rama actualizada correctamente."
    git push origin $rama
  else
    echo "Error al actualizar la rama $rama.  Resuelve los conflictos manualmente y ejecuta 'git push origin $rama'"
  fi
done

echo "Actualización de ramas completada."
